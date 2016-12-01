package com.norg.cookbook.service;

import com.norg.cookbook.entity.Recipe;
import com.norg.cookbook.repository.IngredientRepository;
import com.norg.cookbook.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Norg on 01.12.2016.
 */
@Service
public class RecipeService {
    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;

    @Autowired
    private void setRecipeRepository(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Autowired
    public void setIngredientRepository(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Transactional
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Transactional
    public void delete(Recipe recipe) {
        recipeRepository.delete(recipe);
    }

    @Transactional
    public void delete(Integer id) {
        recipeRepository.delete(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Recipe> list() {
        return new ArrayList<>((Collection<? extends Recipe>) recipeRepository.findAll());
    }

    @Transactional
    public void clear() {
        recipeRepository.deleteAll();
    }
}
