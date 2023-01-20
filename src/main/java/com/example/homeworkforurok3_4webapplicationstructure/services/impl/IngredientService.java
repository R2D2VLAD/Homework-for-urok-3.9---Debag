package com.example.homeworkforurok3_4webapplicationstructure.services.impl;

import com.example.homeworkforurok3_4webapplicationstructure.model.Ingredient;
import com.example.homeworkforurok3_4webapplicationstructure.services.IndredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientService implements IndredientService {
    private static Map<Long, Ingredient> ingredientMap = new HashMap<>();

    public static long id = 0;

    @Override
    public long addIngredient(Ingredient ingredient) {
        ingredientMap.put(id++, ingredient);
        return id++;
    }

    @Override
    public Ingredient getIngredient(long id) {
        for (Ingredient ingredient : ingredientMap.values()) {
            ingredientMap.get(id);
            if (ingredient != null) {
                return ingredient;
            }
        }
        return null;
    }
}



