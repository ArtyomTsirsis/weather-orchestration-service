package com.example.weatherorchestrationservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherClient {

    private final HttpClient httpClient;

}
