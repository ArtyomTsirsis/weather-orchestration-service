package com.example.weatherorchestrationservice.controller;

import com.example.weatherorchestrationservice.core.WeatherInfoService;
import com.example.weatherorchestrationservice.dto.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
@Validated
public class WeatherController {

    private final WeatherInfoService weatherInfoService;

    @GetMapping
    public WeatherResponse getWeather(@RequestParam @Range(min = -90, max = 90) double lat,
                                      @RequestParam @Range(min = -180, max = 180) double lon) {
        return weatherInfoService.getWeather(lat, lon);
    }

    @ResponseStatus
    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException exception) {
        return exception.getMessage();
    }

}
