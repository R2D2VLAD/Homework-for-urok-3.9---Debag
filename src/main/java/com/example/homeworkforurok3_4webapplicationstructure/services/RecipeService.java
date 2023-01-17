package com.example.homeworkforurok3_4webapplicationstructure.services;

import com.example.homeworkforurok3_4webapplicationstructure.model.Recipe;

import java.util.HashMap;
import java.util.Map;

public class RecipeService {
   private static Map<Long, Recipe> recipeMap = new HashMap<>();

    public static long id = 0;

    public void addRecipe(Recipe recipe) {
        recipeMap.put(id++, recipe);
    }

    public void getRecipe(Long id) {
        recipeMap.get(id);
    }
}
