package com.example.weatherorchestrationservice.client;

import com.example.weatherorchestrationservice.dto.WeatherFromYrResponse;
import com.example.weatherorchestrationservice.exception.RequestFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
@RequiredArgsConstructor
public class WeatherClient extends HttpClient {

    private final String siteName = System.getenv("User-Agent");

    public ResponseEntity<WeatherFromYrResponse> getWeather(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", siteName);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<WeatherFromYrResponse> response;
        try {
            response = exchange(url, HttpMethod.GET, entity, WeatherFromYrResponse.class);
        }
        catch (HttpClientErrorException exception) {
            throw  new RequestFailedException(String.format("Request to Yr failed: %d. %s", exception.getRawStatusCode(), exception.getStatusText()));
        }
        return response;
    }

}


