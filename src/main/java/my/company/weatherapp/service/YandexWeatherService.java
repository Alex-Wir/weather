package my.company.weatherapp.service;

import lombok.SneakyThrows;
import my.company.weatherapp.model.Weather;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YandexWeatherService implements WeatherService {

    private String cityName;

    public YandexWeatherService(@Value("${app.city-name}") String cityName) {
        this.cityName = cityName;
    }

    @SneakyThrows
    @Override
    public List<Weather> gWeather() {
        Document doc = Jsoup.connect(String.format("https://yandex.ru/pogoda/%s", cityName)).get();
        Element tempValue = doc.selectFirst(".temp__value");
        return List.of(new Weather("YandexWeather", cityName, tempValue.text()));
    }
}