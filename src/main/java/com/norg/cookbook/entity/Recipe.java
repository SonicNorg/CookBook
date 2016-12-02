package com.norg.cookbook.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Рецепт. Методы синхронизированы, потому что по задумке один рецепт не могут менять одновременно несколько пользователей.
 */
@Entity
public class Recipe {
    private Integer id;
    private String name;
    private String description;
    private List<RecipeIngredient> ingredients = new ArrayList<>();
    //TODO date, author, etc.

    public Recipe() {
    }

    public Recipe(String name) {
        this.name = name;
    }

    public Recipe(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public synchronized void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized String getDescription() {
        return description;
    }

    public synchronized void setDescription(String description) {
        this.description = description;
    }

    //с ленивой загрузкой не проходил тест (валился при попытке вывести рецепт на экран), еще не разбирался, почему
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
//    @OneToMany(fetch = FetchType.EAGER)
    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }

    //    public synchronized void addIngredient(Ingredient ingredient, Float amount) {
//        ingredients.put(ingredient, amount);
//    }
//
//    public synchronized void removeIngredient(Ingredient ingredient) {
//        ingredients.remove(ingredient);
//    }
}
