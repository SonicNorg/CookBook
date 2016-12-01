package com.norg.cookbook.repository;

import com.norg.cookbook.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Репозиторий ингредиента
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

}
