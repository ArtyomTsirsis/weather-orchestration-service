package com.example.weatherorchestrationservice.client;

import com.example.weatherorchestrationservice.dto.WeatherFromYrResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherClient {

    private final HttpClient httpClient;
    @Value(("${user-agent}"))
    private String siteName;

    public ResponseEntity<WeatherFromYrResponse> getWeather(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", siteName);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        return httpClient.exchange(url, HttpMethod.GET, entity, WeatherFromYrResponse.class);
    }

}


