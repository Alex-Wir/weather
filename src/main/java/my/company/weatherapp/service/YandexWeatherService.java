package my.company.weatherapp.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import my.company.weatherapp.model.Weather;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class YandexWeatherService implements WeatherService {

    @SneakyThrows
    @Override
    public Weather getWeather(String city) {
        try {
            Document doc = Jsoup.connect(String.format("https://yandex.ru/pogoda/%s", city)).get();
            Element tempValue = doc.selectFirst(".temp__value");
            Weather weather = new Weather("YandexWeather", tempValue.text());
            log.debug("Successful request YandexWeather: city = {}, weather = {}", city, weather);
            return weather;
        } catch (Exception e) {
            log.error("Error during request YandexWeather: city = {}, {}", city, e.getMessage());
            return Weather.empty();
        }
    }
}