package com.norg.cookbook.repository;

import com.norg.cookbook.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий ингредиента
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

}
