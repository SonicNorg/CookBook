package com.norg.cookbook.service;

import com.norg.cookbook.entity.Ingredient;
import com.norg.cookbook.repository.IngredientRepository;
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
public class IngredientService {
    private IngredientRepository ingredientRepository;

    @Autowired
    public void setIngredientRepository(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Transactional
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Transactional
    public void delete(Ingredient ingredient) {
        ingredientRepository.delete(ingredient);
    }

    @Transactional
    public void delete(Integer id) {
        ingredientRepository.delete(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Ingredient> list() {
        return new ArrayList<>((Collection<? extends Ingredient>) ingredientRepository.findAll());
    }

    @Transactional
    public void clear() {
        ingredientRepository.deleteAll();
    }
}
