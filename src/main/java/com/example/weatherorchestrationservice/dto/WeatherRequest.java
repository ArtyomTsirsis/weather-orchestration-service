package com.example.weatherorchestrationservice.dto;

import org.hibernate.validator.constraints.Range;

public class WeatherRequest {

    @Range(min = -90, max = 90)
    double latitude;
    @Range(min = -180, max = 180)
    double longitude;

}