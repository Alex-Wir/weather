package my.company.weatherapp.service;

import my.company.weatherapp.model.Weather;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class WeatherCache {

    private final Map<String, List<Weather>> weatherCached;

    public WeatherCache() {
        this.weatherCached = new HashMap<>();
    }

    public void putValue(String city, List<Weather> weather) {
        if (!weather.isEmpty()) {
            synchronized (weatherCached) {
                weatherCached.put(city, weather);
            }
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            executor.schedule(() -> resetCache(city), 30, TimeUnit.MINUTES);
        }
    }

    public synchronized Optional<List<Weather>> getValue(String city) {
        return Optional.ofNullable(weatherCached.get(city));
    }

    private synchronized void resetCache(String city) {
        weatherCached.remove(city);
    }
}