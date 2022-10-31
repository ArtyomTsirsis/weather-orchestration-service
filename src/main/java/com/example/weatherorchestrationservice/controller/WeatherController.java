package com.example.weatherorchestrationservice.controller;

import com.example.weatherorchestrationservice.core.WeatherHandler;
import com.example.weatherorchestrationservice.dto.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
