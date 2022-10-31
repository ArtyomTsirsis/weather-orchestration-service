package com.example.weatherorchestrationservice.controller;

import com.example.weatherorchestrationservice.core.WeatherHandler;
import com.example.weatherorchestrationservice.dto.WeatherRequest;
import com.example.weatherorchestrationservice.dto.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherHandler weatherHandler;

    @GetMapping(value = "/")
    public WeatherResponse getWeather(@RequestBody @Valid WeatherRequest request) {
        return weatherHandler.getWeather(request);
    }

}
