package com.example.homeworkforurok3_4webapplicationstructure.controllers;

import com.example.homeworkforurok3_4webapplicationstructure.services.impl.FilesServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/workFiles")
@Tag(name = "Импорт & Экспорт", description = "Endpoint-ы для импортирования и экспортирования данных")
public class FilesController {

    private final FilesServiceImpl filesService;

    public FilesController(FilesServiceImpl filesService) {
        this.filesService = filesService;
    }

    @Value("${path.to.recipe.file}")
    private String dataFilePath1;

    @Value("${name.of.recipe.file}")
    private String dataFileName1;

    @Value("${path.to.ingredient.file}")
    private String dataFilePath2;

    @Value("${name.of.ingredient.file}")
    private String dataFileName2;



    @GetMapping("/recipeExport")
    @Operation(summary = "Endpoint для выгрузки рецептов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Всё хорошо, запрос выполнился!",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Есть ошибка в параметрах запроса",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "URL неверный или такого действия в приложении нет!",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере!",
                    content = {
                            @Content(mediaType = "application/json")}),})
    public ResponseEntity<InputStreamResource> downloadRecipeFile() throws FileNotFoundException {
        File file = filesService.getDataFile(new File(dataFilePath1), new File(dataFileName1));
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \"Recipe.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/recipeImport", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Endpoint для загрузки рецептов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Всё хорошо, запрос выполнился!",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Есть ошибка в параметрах запроса",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "URL неверный или такого действия в приложении нет!",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере!",
                    content = {
                            @Content(mediaType = "application/json")}),})
    public ResponseEntity<Void> uploadRecipe(@RequestParam MultipartFile file) {
        filesService.cleanDataFile(dataFilePath1, dataFileName1);
        File dataFile = filesService.getDataFile(new File(dataFilePath1), new File(dataFileName1));

        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(value = "/ingredientImport", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Endpoint для загрузки ингредиентов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Всё хорошо, запрос выполнился!",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Есть ошибка в параметрах запроса",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "URL неверный или такого действия в приложении нет!",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере!",
                    content = {
                            @Content(mediaType = "application/json")}),})
    public ResponseEntity<Void> uploadIngredient(@RequestParam MultipartFile file) {
        filesService.cleanDataFile(dataFilePath2, dataFileName2);
        File dataFile = filesService.getDataFile(new File(dataFilePath2), new File(dataFileName2));

        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
