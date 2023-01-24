package com.example.homeworkforurok3_4webapplicationstructure.services.impl;
import com.example.homeworkforurok3_4webapplicationstructure.model.Recipe;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeService implements com.example.homeworkforurok3_4webapplicationstructure.services.RecipeService {
    private static final Map<Long, Recipe> recipeMap = new HashMap<>();

    public static long id = 0;

    @Override
    public long addRecipe(Recipe recipe) {
        recipeMap.put(id, recipe);
        return id++;
    }

    @Override
    public Recipe getRecipe(long id) {
        for (Recipe ignored : recipeMap.values()) {
            Recipe recipe1 = recipeMap.get(id);
            if (recipe1 != null) {
                return recipe1;
            }
        }
        return null;
    }

    @Override
    public Recipe editRecipe(long id, Recipe recipe) {
        for (Map.Entry<Long, Recipe> ignored : recipeMap.entrySet()) {
            if (recipeMap.containsKey(id)) {
                recipeMap.put(id, recipe);
                return recipe;
            }
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(long id) {
        for (Recipe ignored : recipeMap.values()) {
            if (recipeMap.containsKey(id)) {
                recipeMap.remove(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public Recipe getAllRecipe() {
        for (Map.Entry<Long, Recipe> entry : recipeMap.entrySet()) {
            return entry.getValue();
        }
        return null;
    }
}
