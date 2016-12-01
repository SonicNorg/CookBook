package com.norg.cookbook.repository;

import com.norg.cookbook.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Репозиторий рецепта
 */
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
}
