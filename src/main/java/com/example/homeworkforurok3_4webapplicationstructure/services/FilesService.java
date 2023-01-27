package com.example.homeworkforurok3_4webapplicationstructure.services;

public interface FilesService {

    boolean saveToFile(String json, String dataFilePath, String dataFileName);

    String readFromFile(String dataFilePath, String dataFileName);
}
