package my.company.weatherapp.controller;

import my.company.weatherapp.model.Weather;
import my.company.weatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherRestController {

    private final WeatherService weatherService;

    public WeatherRestController(@Qualifier("weatherAggregationService") WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public List<Weather> getWeather() {
        return weatherService.gWeather();
    }
}