package com.example.weatherorchestrationservice.controller;

import com.example.weatherorchestrationservice.dto.WeatherRequest;
import com.example.weatherorchestrationservice.dto.WeatherResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class WeatherController {

    @GetMapping
    public WeatherResponse getWeather(@RequestBody @Valid WeatherRequest request) {

    }

}
