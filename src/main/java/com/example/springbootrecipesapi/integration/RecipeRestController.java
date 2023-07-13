package com.example.springbootrecipesapi.integration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class RecipeRestController {

    //Get a random recipe on the "Needing Inspiration?" page
    @GetMapping(value = "/recipes-random")
    public String getRandomRecipe() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://themealdb.p.rapidapi.com/random.php"))
                .header("X-RapidAPI-Key", "b9a6348ac4msh1bcdc78affac152p185529jsn9d2cbdd26316")
                .header("X-RapidAPI-Host", "themealdb.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    //Popular recipes for the Homepage
    @GetMapping(value = "/popular-recipes")
    private String getTopRecipes() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://themealdb.p.rapidapi.com/latest.php"))
                .header("X-RapidAPI-Key", "b9a6348ac4msh1bcdc78affac152p185529jsn9d2cbdd26316")
                .header("X-RapidAPI-Host", "themealdb.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    @GetMapping(value = "/get-recipe-by-name/{recipeName}")
    private String getRecipeByName(@PathVariable("recipeName") String rawDishName) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://themealdb.p.rapidapi.com/search.php?s=" + rawDishName))
                .header("X-RapidAPI-Key", "b9a6348ac4msh1bcdc78affac152p185529jsn9d2cbdd26316")
                .header("X-RapidAPI-Host", "themealdb.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body();
    }
}
