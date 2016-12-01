package com.norg.cookbook.dao;

import com.norg.cookbook.model.Ingredient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Тесты ДАО ингредиента
 */
public class IngredientDaoTest {
    private ApplicationContext context;

    @Before
    public void getSpringContext() {
        context = new ClassPathXmlApplicationContext("spring-conf.xml");
    }

    @Test
    public void addTest() {
        IngredientDao dao = (IngredientDao) context.getBean("ingredientDao");
        dao.add(new Ingredient("Potato", "kg"));
        dao.add(new Ingredient("Tomato", "kg"));
    }

}
