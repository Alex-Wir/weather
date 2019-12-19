package my.company.weatherapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CityWeather {

    private String city;
    private List<Weather> weatherList;
}