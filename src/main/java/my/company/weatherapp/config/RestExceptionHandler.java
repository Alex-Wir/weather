package my.company.weatherapp.config;

import my.company.weatherapp.controller.CityNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({CityNameException.class})
    public ResponseEntity<String> wrongCity(CityNameException e) {
        return error(HttpStatus.BAD_REQUEST, "Wrong city name");
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> cityNotFound(Exception e) {
        return error(HttpStatus.NOT_FOUND, "City not found");
    }

    private ResponseEntity<String> error(HttpStatus status, String message) {
        return ResponseEntity
                .status(status)
                .body(message);
    }
}