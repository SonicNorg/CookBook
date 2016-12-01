package com.norg.cookbook.repository;

import com.norg.cookbook.model.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий рецепта
 */
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
}
