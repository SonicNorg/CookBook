package com.norg.cookbook.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Рецепт. Методы синхронизированы, потому что по задумке один рецепт не могут менять одновременно несколько пользователей.
 */
public class Recipe {
    private int id;
    private Map<Ingredient, Float> ingredients = new HashMap<Ingredient, Float>();
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized void addIngredient(Ingredient ingredient, Float amount) {
        ingredients.put(ingredient, amount);
    }

    public synchronized void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    public synchronized String getDescription() {
        return description;
    }

    public synchronized void setDescription(String description) {
        this.description = description;
    }

    public Recipe(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Recipe(String name) {
        this.name = name;
    }

    public Recipe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Ingredient, Float> getIngredients() {
        return ingredients;
    }
}
