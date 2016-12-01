package com.norg.cookbook.repository;

import com.norg.cookbook.model.Ingredient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Тесты репозитория ингредиента
 */
public class IngredientRepositoryTest {
    private ApplicationContext context;

    @Before
    public void getSpringContext() {
        context = new ClassPathXmlApplicationContext("spring-conf.xml");
    }

    @Test
    public void addTest() {
        IngredientRepository dao = (IngredientRepository) context.getBean("ingredientRepository");
        Ingredient potato = dao.save(new Ingredient("Potato"));
        Ingredient tomato = dao.save(new Ingredient("Tomato"));
        System.out.println(dao.findOne(potato.getId()));
        System.out.println(dao.findOne(tomato.getId()));

    }

}
