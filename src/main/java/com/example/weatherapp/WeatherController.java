package com.example.weatherapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherController {

    private static final String API_KEY = "API_KEY"; 

    @GetMapping("/getWeather")
    public String getWeather(@RequestParam("city") String city, Model model) {
        String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&units=metric";
        RestTemplate restTemplate = new RestTemplate();

        try {
            WeatherResponse weatherData = restTemplate.getForObject(apiUrl, WeatherResponse.class);
            
            System.out.println("Weather API Response: " + weatherData);
            
            model.addAttribute("weatherData", weatherData); 
            model.addAttribute("city", city); 
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            model.addAttribute("error", "Could not retrieve weather data for " + city + ". Please check the city name and try again.");
        }

        return "index"; 
    }
}
