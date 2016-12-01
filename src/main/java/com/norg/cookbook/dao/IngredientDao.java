package com.norg.cookbook.dao;

import com.norg.cookbook.model.Ingredient;

import java.util.List;

/**
 * ДАО ингредиента
 */
public interface IngredientDao {
    Ingredient add(Ingredient ingredient);
    void delete(int id);
    Ingredient getById(int id);
    List<Ingredient> getList();
}
