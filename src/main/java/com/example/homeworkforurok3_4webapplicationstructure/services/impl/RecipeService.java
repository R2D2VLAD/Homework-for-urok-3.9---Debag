package com.example.homeworkforurok3_4webapplicationstructure.services.impl;

import com.example.homeworkforurok3_4webapplicationstructure.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeService implements com.example.homeworkforurok3_4webapplicationstructure.services.RecipeService {
   private static Map<Long, Recipe> recipeMap = new HashMap<>();

    public static long id = 0;

    @Override
    public long addRecipe(Recipe recipe) {
        recipeMap.put(id++, recipe);
        return id++;
    }

    @Override
    public Recipe getRecipe(long id) {
        for (Recipe recipe : recipeMap.values()) {
            recipeMap.get(id);
            if (recipe != null) {
                return recipe;
            }
        }
        return null;
    }
}
