package com.norg.cookbook.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Запись "Ингредиент рецепта", для хранения элемента в рецепте с количеством и единицей измерения
 */
@Entity
public class RecipeIngredient {
    private Integer id;
    private Recipe recipe;
    private Ingredient ingredient;
    private BigDecimal quantity;
    private String unit;

    public RecipeIngredient() {
    }

    public RecipeIngredient(Ingredient ingredient, BigDecimal quantity, String unit) {
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    @ManyToOne(cascade = {CascadeType.ALL})
    @ManyToOne
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @ManyToOne (cascade = {CascadeType.ALL})
//    @ManyToOne
    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
