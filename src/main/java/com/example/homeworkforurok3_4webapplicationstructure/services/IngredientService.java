package com.example.homeworkforurok3_4webapplicationstructure.services;

import com.example.homeworkforurok3_4webapplicationstructure.model.Ingredient;
import com.example.homeworkforurok3_4webapplicationstructure.model.Recipe;
import org.yaml.snakeyaml.events.Event;

import java.util.HashMap;
import java.util.Map;

public class IngredientService {
    private static Map<Long, Ingredient> ingredientMap = new HashMap<>();

    public static long id = 0;

    public void addIngredient(Ingredient ingredient) {
        ingredientMap.put(id++, ingredient);
    }

    public void getIngredient(Long id) {
        ingredientMap.get(id);
    }
}
