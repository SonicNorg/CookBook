package com.norg.cookbook.controller;

import com.norg.cookbook.entity.Ingredient;
import com.norg.cookbook.entity.Recipe;
import com.norg.cookbook.entity.RecipeIngredient;
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

import java.math.BigDecimal;

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
    public String listIngredients(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("ingredients", ingredientService.list());
        return "ingredients";
    }

    @RequestMapping(value = "/add_ingredient", method = RequestMethod.POST)
    public String addIngredient(@ModelAttribute("ingredient")Ingredient ingredient) {
        //по-хорошему надо не просто молча пропускать, если пустое имя, а валидировать и сообщать пользователю
        if(!"".equals(ingredient.getName()) || ingredient.getName() == null) {
            ingredientService.save(ingredient);
        }
        return "redirect:/ingredients";
    }

    @RequestMapping("/remove_ingredient/{id}")
    public String removeIngredient(@PathVariable("id") Integer id) {
        try{
            ingredientService.delete(id);
        }catch (Exception e) {
            return "cannotdelete";
        }
        return "redirect:/ingredients";
    }

    @RequestMapping("/edit_ingredient/{id}")
    public String editIngredient(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ingredient", ingredientService.findById(id));
        model.addAttribute("ingredients", ingredientService.list());

        return "ingredients";
    }

    @RequestMapping(value = "/recipes", method = RequestMethod.GET)
    public String listRecipes(Model model) {
        model.addAttribute("recipes", recipeService.list());
        model.addAttribute("ingredients", ingredientService.list());
        return "recipes";
    }

    @RequestMapping(value = "/add_recipe", method = RequestMethod.POST)
    public String addRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("ingredients", ingredientService.list());
        return "recipe";
    }

    @RequestMapping(value = "/save_recipe", method = RequestMethod.POST)
    public String saveRecipe(@ModelAttribute("recipe")Recipe recipe) {
        //по-хорошему надо не просто молча пропускать, если пустое имя, а валидировать и сообщать пользователю
        if(!"".equals(recipe.getName())) {
            recipeService.save(recipe);
        }
        return "redirect:/recipes";
    }

    @RequestMapping(value = "/take/{id}", method = RequestMethod.POST)
    public String takeToRecipe(@PathVariable("id") Integer id, @ModelAttribute("recipe")Recipe recipe, Model model) {
        Ingredient beingAdded = ingredientService.findById(id);
        recipe.getIngredients().add(new RecipeIngredient(beingAdded, new BigDecimal(0), ""));
        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", ingredientService.list());
        return "redirect:/recipe";
    }
}
