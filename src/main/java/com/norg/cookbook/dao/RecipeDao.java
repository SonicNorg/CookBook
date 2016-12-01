package com.norg.cookbook.dao;

import com.norg.cookbook.model.Recipe;

import java.util.List;

/**
 * ДАО рецепта
 */
public interface RecipeDao {
    Recipe add(Recipe recipe);
    void delete(int id);
    Recipe getById(int id);
    List<Recipe> getList();
}
