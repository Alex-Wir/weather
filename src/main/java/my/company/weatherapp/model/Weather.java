package my.company.weatherapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Weather {

    private final String source;
    private final String temperature;

    public static Weather empty() {
        return new Weather("", "");
    }
}