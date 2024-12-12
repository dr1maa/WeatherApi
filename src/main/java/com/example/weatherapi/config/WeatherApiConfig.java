package com.example.weatherapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WeatherApiConfig {

    @Value("${weatherapi.base-url}")
    private String baseUrl;

    @Value("${weatherapi.api-key}")
    private String apiKey;

    @Bean
    public WebClient weatherApiClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("key", apiKey)
                .build();
    }
}