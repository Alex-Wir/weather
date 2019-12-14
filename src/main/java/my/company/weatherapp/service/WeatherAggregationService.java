package my.company.weatherapp.service;

import lombok.RequiredArgsConstructor;
import my.company.weatherapp.model.Weather;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WeatherAggregationService implements WeatherService {

    private final List<WeatherService> weatherServices;

    @Override
    public List<Weather> gWeather(String city) {
        return weatherServices
                .stream()
                .flatMap(ws -> ws.gWeather(city).stream())
                .collect(Collectors.toList());
    }
}