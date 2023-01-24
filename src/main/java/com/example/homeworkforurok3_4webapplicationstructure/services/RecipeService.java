package com.example.homeworkforurok3_4webapplicationstructure.services;

import com.example.homeworkforurok3_4webapplicationstructure.model.Recipe;

public interface RecipeService {
    long addRecipe(Recipe recipe);

    Recipe getRecipe(long id);

    Recipe editRecipe(long id, Recipe recipe);

    boolean deleteRecipe(long id);

    Recipe getAllRecipe();
}
