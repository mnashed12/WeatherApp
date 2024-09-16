package com.example.weatherapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherController {

    private static final String API_KEY = "b1d27621d9762a119758216b387b396e"; 

    @GetMapping("/getWeather")
    public String getWeather(@RequestParam("city") String city, Model model) {
        String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&units=metric";
        RestTemplate restTemplate = new RestTemplate();

        try {
            WeatherResponse weatherData = restTemplate.getForObject(apiUrl, WeatherResponse.class);
            
            // Debug: print response to console
            System.out.println("Weather API Response: " + weatherData);
            
            model.addAttribute("weatherData", weatherData); // Pass weather data to the view
            model.addAttribute("city", city); // Pass the city name to the view
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            model.addAttribute("error", "Could not retrieve weather data for " + city + ". Please check the city name and try again.");
        }

        return "index"; // Return to the index.html page
    }
}
