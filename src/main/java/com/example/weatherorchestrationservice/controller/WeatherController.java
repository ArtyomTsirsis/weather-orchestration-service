package com.example.weatherorchestrationservice.controller;

import com.example.weatherorchestrationservice.core.WeatherHandler;
import com.example.weatherorchestrationservice.dto.WeatherResponse;
import com.example.weatherorchestrationservice.exception.MappingFailedException;
import com.example.weatherorchestrationservice.exception.RequestFailedException;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
@Validated
public class WeatherController {

    private final WeatherHandler weatherHandler;

    @GetMapping()
    public WeatherResponse getWeather(@RequestParam @Range(min = -90, max = 90) double lat,
                                      @RequestParam @Range(min = -180, max = 180) double lon) {
        return weatherHandler.getWeather(lat, lon);
    }

    @ExceptionHandler({RequestFailedException.class, MappingFailedException.class, ConstraintViolationException.class, MethodArgumentTypeMismatchException.class})
    public String handleException(RuntimeException exception) {
        return exception.getMessage();
    }

}
