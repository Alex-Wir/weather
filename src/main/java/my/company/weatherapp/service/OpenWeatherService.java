package my.company.weatherapp.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import my.company.weatherapp.model.Weather;
import my.company.weatherapp.dto.OpenWeatherDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OpenWeatherService implements WeatherService {

    private final RestTemplate restTemplate;

    @Value("${app.openweather-api-key}")
    private String apiKey;

    @Override
    public List<Weather> gWeather(String city) {
        val url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&lang=ru&appid=%s",
                city, apiKey);

        val dto = restTemplate.getForObject(url, OpenWeatherDto.class);
        return Collections.singletonList(toModel(dto, city));
    }

    private Weather toModel(OpenWeatherDto dto, String city) {
        return new Weather("OpenWeather", city, dto.getMain().getTemperature());
    }
}