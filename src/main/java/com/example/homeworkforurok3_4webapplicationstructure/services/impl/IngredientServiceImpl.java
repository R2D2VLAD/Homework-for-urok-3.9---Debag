package com.example.homeworkforurok3_4webapplicationstructure.services.impl;

import com.example.homeworkforurok3_4webapplicationstructure.model.Ingredient;
import com.example.homeworkforurok3_4webapplicationstructure.services.IndredientService;
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
public class IngredientServiceImpl implements IndredientService {

    private final FilesServiceImpl filesService;
    private static Map<Long, Ingredient> ingredientMap = new HashMap<>();

    public static long id = 0;

    public IngredientServiceImpl(FilesServiceImpl filesService) {
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
    public long addIngredient(Ingredient ingredient) {
        ingredientMap.put(id, ingredient);
        saveToFile();
        return id++;
    }

    @Override
    public Ingredient getIngredient(long id) {
        return ingredientMap.get(id);
    }

    @Override
    public Ingredient editIngredient(long id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id)) {
            ingredientMap.put(id, ingredient);
            saveToFile();
            return ingredient;
        }
        return null;
    }

    @Override
    public boolean deleteIngredient(long id) {
        if (ingredientMap.containsKey(id)) {
            ingredientMap.remove(id);
            saveToFile();
            return true;
        }
        return false;
    }

    @Override
    public Collection<Ingredient> getAllIngredient() {
        return ingredientMap.values();
    }

    @Value("${path.to.ingredient.file}")
    private String dataFilePath;
    @Value("${name.of.ingredient.file}")
    private String dataFileName;

    private void saveToFile() {
        try {
            DataFile dataFile = new DataFile(id + 1, ingredientMap);
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
            ingredientMap = dataFile.getIngredientMap();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class DataFile {
        private long id;
        private Map<Long, Ingredient> ingredientMap;
    }
}



