package com.example.weatherapi.controller;

import com.example.weatherapi.dto.WeatherResponse;
import com.example.weatherapi.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{cityId}/current")
    public ResponseEntity<WeatherResponse> getCurrentWeather(@PathVariable Long cityId) {
        return ResponseEntity.ok(weatherService.getCurrentWeather(cityId));
    }
}