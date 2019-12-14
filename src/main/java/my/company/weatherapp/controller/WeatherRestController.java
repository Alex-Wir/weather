package my.company.weatherapp.controller;

import my.company.weatherapp.model.Weather;
import my.company.weatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherRestController {

    private final WeatherService weatherService;

    public WeatherRestController(@Qualifier("weatherAggregationService") WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public List<Weather> getMinskWeather() {
        return weatherService.gWeather("Minsk");
    }

    @GetMapping("/weather/{city}")
    public List<Weather> getWeather(@PathVariable String city) {
        if (!isCityName(city)) {
            throw new CityNameException();
        }
        return weatherService.gWeather(city);
    }

    private boolean isCityName(String city) {
        return city.chars().allMatch(Character::isLetter);
    }
}