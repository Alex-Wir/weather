package my.company.weatherapp.service;

import my.company.weatherapp.model.Weather;

public interface WeatherService {

    Weather getWeather(String city);
}