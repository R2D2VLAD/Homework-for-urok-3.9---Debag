package com.example.homeworkforurok3_4webapplicationstructure.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ingredient {
    private String ingredientName;
    private Integer numberIngredients;
    private String unitMeasure;
}
