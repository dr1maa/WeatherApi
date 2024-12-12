package com.example.weatherapi.service;

import com.example.weatherapi.dto.CityRequest;
import com.example.weatherapi.entity.City;
import com.example.weatherapi.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City createCity(CityRequest request) {
        City city = toEntity(request); // Используем метод преобразования
        return cityRepository.save(city);
    }

    public List<City> getCities() {
        return cityRepository.findAll();
    }

    private City toEntity(CityRequest request) {
        City city = new City();
        city.setName(request.name());
        return city;
    }
}