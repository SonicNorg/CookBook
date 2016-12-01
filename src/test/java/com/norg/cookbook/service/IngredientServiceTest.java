package com.norg.cookbook.service;

import com.norg.cookbook.entity.Ingredient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Norg on 01.12.2016.
 */
public class IngredientServiceTest {
    private static ApplicationContext context;
    private IngredientService ingredientService;

    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext("spring-conf.xml");
    }

    @Before
    public void setContext() {
        ingredientService = (IngredientService) context.getBean("ingredientService");
        ingredientService.clear();
    }

    @Test
    public void addTest() {
        ingredientService.save(new Ingredient("Cabbage"));
        Assert.assertEquals("Count check", 1, ingredientService.list().size());
    }
}
