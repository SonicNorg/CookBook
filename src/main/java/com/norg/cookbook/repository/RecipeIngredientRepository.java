package com.norg.cookbook.repository;

import com.norg.cookbook.entity.RecipeIngredient;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Norg on 01.12.2016.
 */
public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Integer> {
}
