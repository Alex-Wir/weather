package my.company.weatherapp.controller;

import lombok.AllArgsConstructor;
import my.company.weatherapp.model.CityWeather;
import my.company.weatherapp.service.WeatherAggregationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WeatherRestController {

    private WeatherAggregationService weatherService;

    @GetMapping("/weather")
    public CityWeather getMinskWeather() {
        return weatherService.getWeather("Minsk");
    }

    @GetMapping("/weather/{city}")
    public CityWeather getWeather(@PathVariable String city) {
        if (!isCityName(city)) {
            throw new CityNameException();
        }
        return weatherService.getWeather(city);
    }

    private boolean isCityName(String city) {
        return city.chars().allMatch(Character::isLetter);
    }
}