package com.example.weatherorchestrationservice.core;

import com.example.weatherorchestrationservice.client.WeatherClient;
import com.example.weatherorchestrationservice.domain.CachedEntity;
import com.example.weatherorchestrationservice.dto.CachingServiceResponse;
import com.example.weatherorchestrationservice.repository.CachingRepository;
import com.example.weatherorchestrationservice.utilites.UrlGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherHandlerTest {

    @Mock
    private UrlGenerator generator;
    @Mock
    private WeatherClient client;
    @Mock
    private WeatherCachingService service;
    @InjectMocks
    private WeatherHandler handler;

    /*
     * scenario1
     * input: "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=0&lon=0"
     * expected output: "true", "20.0"
     */
    @Test
    void scenario1() {



        String url = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=0&lon=0";
        CachedEntity entity = new CachedEntity(url, 20.0, LocalDateTime.now());
        CachingServiceResponse expected = new CachingServiceResponse();
        expected.setCacheValid(true);
        expected.setTemperature(20.0);
        when(repository.findById(url)).thenReturn(Optional.of(entity));
        CachingServiceResponse actual = service.checkCache(url);
        assertEquals(expected, actual);
    }

}