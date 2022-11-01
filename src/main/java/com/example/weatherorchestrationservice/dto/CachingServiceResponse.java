package com.example.weatherorchestrationservice.dto;

import lombok.Data;

@Data
public class CachingServiceResponse {

    private boolean cacheValid;
    private double temperature;

}
