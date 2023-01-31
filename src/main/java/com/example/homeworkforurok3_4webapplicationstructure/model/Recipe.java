package com.example.homeworkforurok3_4webapplicationstructure.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private String recipeName;
    private int cookingTime;
    List<String> ingredient = new ArrayList<>();
    List<String> cookingSteps = new ArrayList<>();

    }
