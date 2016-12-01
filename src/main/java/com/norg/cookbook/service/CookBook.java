package com.norg.cookbook.service;

import com.norg.cookbook.dao.IngredientDao;
import com.norg.cookbook.dao.RecipeDao;
import com.norg.cookbook.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Основной класс с логикой.
 */
@Service
public class CookBook {
    @Autowired
    private IngredientDao ingredientDao;
    @Autowired
    private RecipeDao recipeDao;

    @Transactional
    public void addIngredient(Ingredient ingredient) {
        ingredientDao.add(ingredient);
    }
}
