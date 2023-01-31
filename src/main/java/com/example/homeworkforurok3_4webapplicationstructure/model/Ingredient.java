package com.example.homeworkforurok3_4webapplicationstructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private String ingredientName;
    private Integer numberIngredients;
    private String unitMeasure;
}
