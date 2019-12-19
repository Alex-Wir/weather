package my.company.weatherapp.service;

import lombok.RequiredArgsConstructor;
import my.company.weatherapp.model.CityWeather;
import my.company.weatherapp.model.Weather;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WeatherAggregationService {

    private final List<WeatherService> weatherServices;

    public CityWeather getWeather(String city) {
        List<Weather> weatherList = weatherServices
                .stream()
                .map(ws -> ws.getWeather(city))
                .filter(this::notEmpty)
                .collect(Collectors.toList());
        return new CityWeather(city, weatherList);
    }

    private boolean notEmpty(Weather weather) {
        return !weather.equals(Weather.empty());
    }
}