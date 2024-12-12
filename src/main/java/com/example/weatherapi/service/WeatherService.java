package com.example.weatherapi.service;

import com.example.weatherapi.dto.WeatherResponse;
import com.example.weatherapi.entity.City;
import com.example.weatherapi.repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    private final WebClient weatherApiClient;
    private final CityRepository cityRepository;

    public WeatherService(WebClient weatherApiClient, CityRepository cityRepository) {
        this.weatherApiClient = weatherApiClient;
        this.cityRepository = cityRepository;
    }

    public WeatherResponse getCurrentWeather(Long cityId) {
        City city = cityRepository.findById(cityId).orElseThrow(() -> new RuntimeException("City not found"));
        return weatherApiClient.get()
                .uri(uriBuilder -> uriBuilder.path("/current.json").queryParam("q", city.getName()).build())
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .block();
    }
}