package com.example.homeworkforurok3_4webapplicationstructure.services.impl;

import com.example.homeworkforurok3_4webapplicationstructure.model.Ingredient;
import com.example.homeworkforurok3_4webapplicationstructure.services.IndredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientService implements IndredientService {
    private static final Map<Long, Ingredient> ingredientMap = new HashMap<>();

    public static long id = 0;

    @Override
    public long addIngredient(Ingredient ingredient) {
        ingredientMap.put(id, ingredient);
        return id++;
    }

    @Override
    public Ingredient getIngredient(long id) {
        for (Ingredient ignored : ingredientMap.values()) {
            Ingredient ingredient1 = ingredientMap.get(id);
            if (ingredient1 != null) {
                return ingredient1;
            }
        }
        return null;
    }

    @Override
    public Ingredient editIngredient(long id, Ingredient ingredient) {
        for (Ingredient ignored : ingredientMap.values()) {
            if (ingredientMap.containsKey(id)) {
                ingredientMap.put(id, ingredient);
                return ingredient;
            }
        }
        return null;
    }

    @Override
    public boolean deleteIngredient(long id) {
        for (Ingredient ignored : ingredientMap.values()) {
            if (ingredientMap.containsKey(id)) {
                ingredientMap.remove(id);
                return true;
            }
        }
        return false;
    }
    @Override
    public Ingredient getAllIngredient() {
        for (Map.Entry<Long, Ingredient> entry : ingredientMap.entrySet()) {
            return entry.getValue();
        }
        return null;
    }
}



