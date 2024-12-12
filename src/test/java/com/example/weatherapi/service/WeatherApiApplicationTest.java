package com.example.weatherapi.service;

import com.example.weatherapi.entity.City;
import com.example.weatherapi.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
@Transactional
public class WeatherApiApplicationTest {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private City testCity;

    @BeforeEach
    public void setup() {
        testCity = new City();
        testCity.setName("Moscow");
        cityRepository.save(testCity);
    }

    @Test
    public void testGetCurrentWeather() {
        String cityName = "Moscow";
        var currentWeather = weatherService.getCurrentWeather(cityName);

        assertThat(currentWeather).isNotNull();
        assertThat(currentWeather.getCity()).isEqualTo(cityName);
    }

    @Test
    public void testCacheIntegration() {
        String key = "weather:moscow";
        String weatherData = "clear sky";
        redisTemplate.opsForValue().set(key, weatherData);

        String cachedWeather = redisTemplate.opsForValue().get(key);

        assertThat(cachedWeather).isEqualTo(weatherData);
    }

    @Test
    public void testDatabaseIntegration() {
        var cityFromDb = cityRepository.findById(testCity.getId());
        assertThat(cityFromDb).isPresent();
        assertThat(cityFromDb.get().getName()).isEqualTo(testCity.getName());
    }
}