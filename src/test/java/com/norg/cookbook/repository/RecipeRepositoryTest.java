package com.norg.cookbook.repository;

import com.norg.cookbook.entity.Ingredient;
import com.norg.cookbook.entity.Recipe;
import com.norg.cookbook.entity.RecipeIngredient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Тесты репозитория ингредиента
 */
public class RecipeRepositoryTest {
    private static ApplicationContext context;
    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;

    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext("spring-conf.xml");
    }

    @Before
    public void getSpringContext() {
        recipeRepository = (RecipeRepository) context.getBean("recipeRepository");
        ingredientRepository = (IngredientRepository) context.getBean("ingredientRepository");
        recipeRepository.deleteAll();
        ingredientRepository.deleteAll();
    }

    @Test
    public void addTest() {
        Recipe shit = new Recipe("Tomato potato");

        List<RecipeIngredient> ingredients = getRecipeIngredientListForRecipe(shit);
        shit.setIngredients(ingredients);
        recipeRepository.save(shit);
        Assert.assertEquals("Count check", 1, recipeRepository.count());
    }

    private List<RecipeIngredient> getRecipeIngredientListForRecipe(Recipe recipe) {
        Ingredient potato = new Ingredient("Potato");
        Ingredient tomato = new Ingredient("Tomato");
        saveIngredients(potato, tomato); //в рецептах можно использовать только уже существующие в базе ингредиенты

        List<RecipeIngredient> ingredients = new ArrayList<RecipeIngredient>() {
            {
                add(new RecipeIngredient(potato, new BigDecimal(5), "ведро"));
                add(new RecipeIngredient(tomato, new BigDecimal(3), "ведро"));
            }
        };
        return ingredients;
    }

    private void saveIngredients(Ingredient... ingredients) {
        for (Ingredient ingredient : ingredients) {
            ingredientRepository.save(ingredient);
        }
    }
}
