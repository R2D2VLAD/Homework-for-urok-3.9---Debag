package com.example.homeworkforurok3_4webapplicationstructure.controllers;
import com.example.homeworkforurok3_4webapplicationstructure.model.Ingredient;
import com.example.homeworkforurok3_4webapplicationstructure.services.impl.IngredientServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты", description = "Endpoint-ы для упраления ингредиентами")
public class IngredientController {

    private final IngredientServiceImpl ingredientService;
    public IngredientController(IngredientServiceImpl ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(
            summary = "Endpoint для добаления ингредиентов")
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
    public ResponseEntity<Long> addIngredient(@RequestBody Ingredient ingredient) {
        long id = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Endpoint для редактирования ингредиентов по id")
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
    public ResponseEntity<Ingredient> editIngredient(@PathVariable long id, @RequestBody Ingredient ingredient) {
        Ingredient ingredient1 = ingredientService.editIngredient(id, ingredient);
        if (ingredient1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Endpoint для удаления ингредиетов по id")
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
    public ResponseEntity<Void> deleteIngredient(@PathVariable long id) {
        if (ingredientService.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Endpoint для получения ингредиентов по id")
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
    public ResponseEntity<Ingredient> getIngredient(@PathVariable long id) {
        Ingredient ingredient = ingredientService.getIngredient(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping
    @Operation(
            summary = "Endpoint получения всех ингредиентов")
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
    public ResponseEntity<Collection<Ingredient>> getAllIngredient() {
        Collection<Ingredient> ingredient = ingredientService.getAllIngredient();
        return ResponseEntity.ok(ingredient);
    }
}
