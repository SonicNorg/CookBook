package com.norg.cookbook.service;

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
 * Тест не изолирован: работает с реальной базой вместо мока.
 */
public class RecipeServiceTest {
    private static ApplicationContext context;
    private RecipeService recipeService;
    private IngredientService ingredientService;

    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext("spring-conf.xml");
    }

    @Before
    public void setContext() {
        recipeService = (RecipeService) context.getBean("recipeService");
        ingredientService = (IngredientService) context.getBean("ingredientService");
        recipeService.clear();
        ingredientService.clear();
    }

    @Test
    public void addTest() {
        Ingredient tomato = new Ingredient("Tomato");
        Ingredient potato = new Ingredient("Potato");
        saveIngredients(tomato, potato);

        Recipe shit = new Recipe("Shit");

        shit.setIngredients(getRecipeIngredients(tomato, potato));

        recipeService.save(shit);
        Assert.assertEquals(1, recipeService.list().size());
    }

    @Test
    public void getTest() {
        Ingredient tomato = new Ingredient("Tomato");
        Ingredient potato = new Ingredient("Potato");
        saveIngredients(tomato, potato);

        Recipe shit = new Recipe("Shit");
        shit.setIngredients(getRecipeIngredients(tomato, potato));
        recipeService.save(shit);
        Assert.assertEquals(1, recipeService.list().size());

        Recipe shitFromDB = recipeService.list().get(0);
        Assert.assertNotNull(shitFromDB);
        Assert.assertNotNull(shitFromDB.getId());

        System.out.println(shitFromDB);
    }

    @Test
    public void delTest() {
        Ingredient tomato = new Ingredient("Tomato");
        Ingredient potato = new Ingredient("Potato");
        saveIngredients(tomato, potato);

        Recipe shit = new Recipe("Shit");
        shit.setIngredients(getRecipeIngredients(tomato, potato));
        recipeService.save(shit);
        Recipe shitFromDB = recipeService.list().get(0);
        recipeService.delete(shitFromDB);

        Assert.assertEquals(0, recipeService.list().size());
    }

    private void saveIngredients(Ingredient... ingredients) {
        for (Ingredient ingredient : ingredients) {
            ingredientService.save(ingredient);
        }
    }

    private List<RecipeIngredient> getRecipeIngredients(final Ingredient tomato, final Ingredient potato) {
        return new ArrayList<RecipeIngredient>() {
            {
                add(new RecipeIngredient(tomato, new BigDecimal(10), "ведро"));
                add(new RecipeIngredient(potato, new BigDecimal(12), "ведро"));
            }
        };
    }
}
