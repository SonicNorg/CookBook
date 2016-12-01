package com.norg.cookbook.dao;

import com.norg.cookbook.model.Ingredient;
import com.norg.cookbook.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Реализация для H2
 */
@Repository
public class RecipeDaoH2 implements RecipeDao {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertRecipe;

    private final RowMapper<RecipeIngredientRow> recipeIngredientRowMapper = (resultSet, i) -> new RecipeIngredientRow(
            resultSet.getInt("recipe_id"),
            resultSet.getString("recipe_name"),
            resultSet.getInt("ingredients_id"),
            resultSet.getString("ingredient_name"),
            resultSet.getString("unit"),
            resultSet.getString("description"),
            resultSet.getFloat("amount")
    );

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        insertRecipe = new SimpleJdbcInsert(dataSource)
                .withTableName("recipes")
                .usingGeneratedKeyColumns("id");
    }

    public Recipe add(Recipe recipe) {
        int id = insertRecipe.executeAndReturnKey(new BeanPropertySqlParameterSource(recipe)).intValue();
        recipe.setId(id);
        for (Map.Entry<Ingredient, Float> entry : recipe.getIngredients().entrySet()) {
            jdbcTemplate.update("INSERT INTO recipe_ingredients (recipe, ingredient, amount) values (?, ?, ?)", recipe.getId(), entry.getKey().getId(), entry.getValue());
        }
        return recipe;
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM recipes WHERE id = ?; DELETE FROM recipe_ingredients WHERE recipe = ?", id, id);
    }

    public Recipe getById(int id) {
        List<RecipeIngredientRow> rows = getRows(id);
        if (rows.isEmpty()) {
            return null;
        }

        List<Recipe> recipes = buildFromRows(rows);
        return recipes.get(0);
    }

    private List<RecipeIngredientRow> getRows() {
        return getRows(0);
    }

    private List<RecipeIngredientRow> getRows(int id) {
        return jdbcTemplate.query(
                "SELECT recipes.id AS recipe_id, recipes.name AS recipe_name, recipes.description AS description, ingredients.id AS ingredients_id, ingredients.name AS ingredients_name, recipe_ingredients.amount AS amount " +
                        "FROM recipes " +
                        "LEFT JOIN recipe_ingredients ON recipe_ingredients.recipe = recipes.id " +
                        "LEFT JOIN ingredients ON recipe_ingredients.ingredient = ingredients.id " +
                        "WHERE (id = ? OR 0 = ?) ORDER By recipe_id", recipeIngredientRowMapper, id, id);
    }

    public List<Recipe> getList() {
        List<RecipeIngredientRow> rows = getRows();
        if (rows.isEmpty()) {
            return Collections.emptyList();
        }

        return buildFromRows(rows);
    }

    private List<Recipe> buildFromRows(List<RecipeIngredientRow> rows) {
        List<Recipe> recipes = new ArrayList<Recipe>();
        Recipe currRecipe = null;
        for (RecipeIngredientRow row : rows) {
            if(currRecipe == null || currRecipe.getId() != row.recipeId) {
                currRecipe = new Recipe(row.recipeId, row.recipeName, row.description);
                recipes.add(currRecipe);
            }
            currRecipe.addIngredient(new Ingredient(row.ingredientId, row.ingredientName, row.unit), row.amount);
        }
        return recipes;
    }

    class RecipeIngredientRow {
        private int recipeId;
        private int ingredientId;
        private String recipeName;
        private String ingredientName;
        private String unit;
        private String description;
        private Float amount;

        public RecipeIngredientRow(int recipeId, String recipeName, int ingredientId, String ingredientName, String unit, String description, Float amount) {
            this.recipeId = recipeId;
            this.recipeName = recipeName;
            this.ingredientId = ingredientId;
            this.ingredientName = ingredientName;
            this.unit = unit;
            this.description = description;
            this.amount = amount;
        }
    }
}
