package com.example.homeworkforurok3_4webapplicationstructure.services;

public interface FilesService {

    boolean saveToFile(String json);

    String readFromFile();
}
