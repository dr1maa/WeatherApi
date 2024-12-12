package com.example.weatherapi.controller;

import com.example.weatherapi.dto.CityRequest;
import com.example.weatherapi.entity.City;
import com.example.weatherapi.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody CityRequest request) {
        return ResponseEntity.ok(cityService.createCity(request));
    }

    @GetMapping
    public ResponseEntity<List<City>> getCities() {
        return ResponseEntity.ok(cityService.getCities());
    }
}