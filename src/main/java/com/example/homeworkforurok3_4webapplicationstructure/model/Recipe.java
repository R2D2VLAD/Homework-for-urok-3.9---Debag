package com.example.homeworkforurok3_4webapplicationstructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Recipe {
    private String recipeName;
    private int cookingTime;
    private Ingredient ingredient;
    List<String> cookingSteps = new ArrayList<>();

}
