package com.example.homeworkforurok3_4webapplicationstructure.services;

import com.example.homeworkforurok3_4webapplicationstructure.model.Recipe;

public interface RecipeService {
    public long addRecipe(Recipe recipe);

    public Recipe getRecipe(long id);
}
