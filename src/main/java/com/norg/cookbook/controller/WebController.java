package com.norg.cookbook.controller;

import com.norg.cookbook.service.IngredientService;
import com.norg.cookbook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Norg on 01.12.2016.
 */
@Controller
@EnableWebMvc
public class WebController {
    private RecipeService recipeService;
    private IngredientService ingredientService;

    @Autowired
    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Autowired
    public void setIngredientService(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "main";
    }
}
