package com.example.homeworkforurok3_4webapplicationstructure.model;

import lombok.Data;

@Data
public class Ingredient {
    private String ingredientName;
    private Integer numberIngredients;
    private String unitMeasure;

    public Ingredient(String ingredientName, int numberIngredients, String unitMeasure) {

        if (ingredientName != null && !ingredientName.isEmpty() && !ingredientName.isBlank()) {
            this.ingredientName = ingredientName;
        }
        this.numberIngredients = numberIngredients;
        if (unitMeasure != null && !unitMeasure.isEmpty() && !unitMeasure.isBlank()) {
            this.unitMeasure = unitMeasure;
        }
    }
}
