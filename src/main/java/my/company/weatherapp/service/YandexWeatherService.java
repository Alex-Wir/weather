package my.company.weatherapp.service;

import lombok.SneakyThrows;
import my.company.weatherapp.model.Weather;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class YandexWeatherService implements WeatherService {

    @SneakyThrows
    @Override
    public Weather getWeather(String city) {
        try {
            Document doc = Jsoup.connect(String.format("https://yandex.ru/pogoda/%s", city)).get();
            Element tempValue = doc.selectFirst(".temp__value");
            return new Weather("YandexWeather", tempValue.text());
        } catch (Exception e) {
            return Weather.empty();
        }
    }
}