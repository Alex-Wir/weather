package my.company.weatherapp.service;

import lombok.SneakyThrows;
import my.company.weatherapp.model.Weather;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YandexWeatherService implements WeatherService {

    @SneakyThrows
    @Override
    public List<Weather> gWeather(String city) {
        Document doc = Jsoup.connect(String.format("https://yandex.ru/pogoda/%s", city)).get();
        Element tempValue = doc.selectFirst(".temp__value");
        return List.of(new Weather("YandexWeather", city, tempValue.text()));
    }
}