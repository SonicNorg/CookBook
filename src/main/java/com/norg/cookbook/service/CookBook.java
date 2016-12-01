package com.norg.cookbook.service;

import com.norg.cookbook.model.Recipe;
import com.norg.cookbook.repository.IngredientRepository;
import com.norg.cookbook.repository.RecipeRepository;
import com.norg.cookbook.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Основной класс с логикой.
 */
@Service
public class CookBook {
    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;

    @Autowired
    public void setIngredientRepository(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Autowired
    public void setRecipeRepository(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Transactional
    public void addIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    @Transactional
    public void addRecipe(Recipe recipe) {

    }
}
