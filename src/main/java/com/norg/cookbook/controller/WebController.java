package com.norg.cookbook.controller;

import com.norg.cookbook.entity.Ingredient;
import com.norg.cookbook.service.IngredientService;
import com.norg.cookbook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/ingredients", method = RequestMethod.GET)
    public String listTasks(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("ingredients", ingredientService.list());
        return "ingredients";
    }

    @RequestMapping(value = "/add_ingredient", method = RequestMethod.POST)
    public String addTask(@ModelAttribute("ingredient")Ingredient ingredient) {
        if(!"".equals(ingredient.getName()) || ingredient.getName() == null) {
            ingredientService.save(ingredient);
        }
        return "redirect:/ingredients";
    }

    @RequestMapping("/remove_ingredient/{id}")
    public String removeTask(@PathVariable("id") Integer id) {
        try{
            ingredientService.delete(id);
        }catch (Exception e) {
            return "cannotdelete";
        }
        return "redirect:/ingredients";
    }

    @RequestMapping("/edit_ingredient/{id}")
    public String editTask(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ingredient", ingredientService.findById(id));
        model.addAttribute("ingredients", ingredientService.list());

        return "ingredients";
    }

}
