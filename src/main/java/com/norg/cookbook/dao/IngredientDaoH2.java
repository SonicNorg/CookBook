package com.norg.cookbook.dao;

import com.norg.cookbook.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Реализация для H2
 */
@Repository
public class IngredientDaoH2 implements IngredientDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertIngredient;
    private final RowMapper<Ingredient> rowMapper = (resultSet, i) -> new Ingredient(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("unit")
    );

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        insertIngredient = new SimpleJdbcInsert(dataSource)
                .withTableName("ingredients")
                .usingGeneratedKeyColumns("id");
    }

    public Ingredient add(Ingredient ingredient) {
        int id = insertIngredient.executeAndReturnKey(new BeanPropertySqlParameterSource(ingredient)).intValue();
        ingredient.setId(id);
        return ingredient;
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM ingredients WHERE id = ?", id);
    }

    public Ingredient getById(int id) {
        List<Ingredient> ingredients = jdbcTemplate.query("SELECT id, name, unit FROM ingredients WHERE id = ?", rowMapper, id
        );
        if (!ingredients.isEmpty()) {
            return ingredients.get(0);
        }
        return null;
    }

    public List<Ingredient> getList() {
        return jdbcTemplate.query("SELECT id, name, unit FROM ingredients", rowMapper);
    }
}
