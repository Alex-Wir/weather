package my.company.weatherapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import my.company.weatherapp.dto.OpenWeatherDto;
import my.company.weatherapp.model.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
@Slf4j
public class OpenWeatherService implements WeatherService {

    private final RestTemplate restTemplate;

    @Value("${app.openweather-api-key}")
    private String apiKey;

    @Override
    public Weather getWeather(String city) {
        try {
            val url = String.format("https://api.openweathermap.org/data/2.5/weatherList?q=%s&units=metric&appid=%s",
                    city, apiKey);
            OpenWeatherDto dto = restTemplate.getForObject(url, OpenWeatherDto.class);
            Weather weather = toModel(dto);
            log.debug("Successful request OpenWeather: city = {}, weather = {}", city, weather);
            return weather;
        } catch (Exception e) {
            log.error("Error during request OpenWeather: city = {}, {}", city, e.getMessage());
            return Weather.empty();
        }
    }

    private Weather toModel(OpenWeatherDto dto) {
        return new Weather("OpenWeather", dto.getMain().getTemperature());
    }
}