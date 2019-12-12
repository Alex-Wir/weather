package my.company.weatherapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Weather {

    private final String source;
    private final String city;
    private final String temperature;
}