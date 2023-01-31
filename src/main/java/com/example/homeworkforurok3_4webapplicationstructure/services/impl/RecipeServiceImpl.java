package com.example.homeworkforurok3_4webapplicationstructure.services.impl;

import com.example.homeworkforurok3_4webapplicationstructure.model.Recipe;
import com.example.homeworkforurok3_4webapplicationstructure.services.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final FilesServiceImpl filesService;
    private static Map<Long, Recipe> recipeMap = new HashMap<>();

    public static long id = 0;

    public RecipeServiceImpl(FilesServiceImpl filesService) {
        this.filesService = filesService;
    }

    @PostConstruct
    private void init() {
        try {
            readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long addRecipe(Recipe recipe) {
        recipeMap.put(id, recipe);
        saveToFile();
        return id++;
    }

    @Override
    public Recipe getRecipe(long id) {
        return recipeMap.get(id);
    }

    @Override
    public Recipe editRecipe(long id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            recipeMap.put(id, recipe);
            saveToFile();
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(long id) {
        if (recipeMap.containsKey(id)) {
            recipeMap.remove(id);
            saveToFile();
            return true;
        }
        return false;
    }

    @Override
    public Collection<Recipe> getAllRecipe() {
        return recipeMap.values();
    }

    @Value("${path.to.recipe.file}")
    private String dataFilePath;

    @Value("${name.of.recipe.file}")
    private String dataFileName;

    private void saveToFile() {
        try {
            DataFile dataFile = new DataFile(id + 1, recipeMap);
            String json = new ObjectMapper().writeValueAsString(dataFile);
            filesService.saveToFile(json, dataFilePath, dataFileName);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = filesService.readFromFile(dataFilePath, dataFileName);
            DataFile dataFile = new ObjectMapper().readValue(json, new TypeReference<>() {
            });
            id = dataFile.getId();
            recipeMap = dataFile.getRecipeMap();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class DataFile {
        private long id;
        private Map<Long, Recipe> recipeMap;
    }
}
