package com.example.homeworkforurok3_4webapplicationstructure.services;

import com.example.homeworkforurok3_4webapplicationstructure.model.Ingredient;

public interface IndredientService {
    long addIngredient(Ingredient ingredient);

    Ingredient getIngredient(long id);
}
