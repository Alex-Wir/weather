package my.company.weatherapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.company.weatherapp.model.CityWeather;
import my.company.weatherapp.model.Weather;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class WeatherAggregationService {

    private final List<WeatherService> weatherServices;
    private final WeatherCache weatherCache;

    public CityWeather getWeather(String city) {
        log.info("Start processing: city = {}", city);
        List<Weather> weatherList = weatherCache.getValue(city)
                .orElseGet(() -> requestServices(city));
        log.info("End processing: city = {}, weather summary = {}", city, weatherList);
        return new CityWeather(city, weatherList);
    }

    private List<Weather> requestServices(String city) {
        List<Weather> weatherList = weatherServices
                .stream()
                .map(ws -> ws.getWeather(city))
                .filter(this::notEmpty)
                .collect(Collectors.toList());
        weatherCache.putValue(city, weatherList);
        return weatherList;
    }

    private boolean notEmpty(Weather weather) {
        return !weather.equals(Weather.empty());
    }
}