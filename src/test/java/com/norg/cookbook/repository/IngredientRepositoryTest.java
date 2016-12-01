package com.norg.cookbook.repository;

import com.norg.cookbook.model.Ingredient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;

/**
 * Тесты репозитория ингредиента
 */
public class IngredientRepositoryTest {
    private static ApplicationContext context;
    private IngredientRepository dao;

    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext("spring-conf.xml");
    }
    @Before
    public void getSpringContext() {
        dao = (IngredientRepository) context.getBean("ingredientRepository");
        dao.deleteAll();
    }

    @Test
    public void addTest() {
        dao.save(new Ingredient("Potato"));
        dao.save(new Ingredient("Tomato"));
        Assert.assertEquals("Count check", 2, dao.count());
    }

    @Test
    public void delByInstanceTest() {
        Ingredient potato = dao.save(new Ingredient("Potato"));
        Assert.assertEquals("Count check", 1, dao.count());
        dao.delete(potato);
        Assert.assertEquals("Count check", 0, dao.count());
    }

    @Test
    public void delByIdTest() {
        Ingredient potato = dao.save(new Ingredient("Potato"));
        Assert.assertEquals("Count check", 1, dao.count());
        dao.delete(potato.getId());
        Assert.assertEquals("Count check", 0, dao.count());
    }

}
