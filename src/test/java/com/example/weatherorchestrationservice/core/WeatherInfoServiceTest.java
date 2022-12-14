package com.example.weatherorchestrationservice.core;

import com.example.weatherorchestrationservice.client.WeatherClient;
import com.example.weatherorchestrationservice.domain.*;
import com.example.weatherorchestrationservice.dto.CachingServiceResponse;
import com.example.weatherorchestrationservice.dto.WeatherFromYrResponse;
import com.example.weatherorchestrationservice.dto.WeatherResponse;
import com.example.weatherorchestrationservice.exception.MappingFailedException;
import com.example.weatherorchestrationservice.utilites.UrlGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherInfoServiceTest {

    @Mock
    private UrlGenerator generator;
    @Mock
    private WeatherCachingService cachingService;
    @Mock
    private WeatherClient client;
    @InjectMocks
    private WeatherInfoService infoService;

    /*
     * scenario1
     * input: "0.0", "0.0"
     * expected output: "20.0"
     */
    @Test
    void scenario1() {
        String url = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=0&lon=0";
        double temperature = 20.0;
        CachingServiceResponse cacheResponse = new CachingServiceResponse();
        cacheResponse.setCacheValid(true);
        cacheResponse.setTemperature(temperature);
        when(generator.generateUrl(0.0, 0.0)).thenReturn(url);
        when(cachingService.checkCache(url)).thenReturn(cacheResponse);
        WeatherResponse expected = new WeatherResponse();
        expected.setTemperature(temperature);
        WeatherResponse actual = infoService.getWeather(0.0, 0.0);
        assertEquals(expected, actual);
    }

    /*
     * scenario1
     * input: "0.0", "0.0"
     * expected output: "20.0"
     */
    @Test
    void scenario2() {
        String url = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=0&lon=0";
        double temperature = 20.0;
        CachingServiceResponse cacheResponse = new CachingServiceResponse();
        cacheResponse.setCacheValid(false);
        WeatherFromYrResponse weatherResponse = new WeatherFromYrResponse();
        weatherResponse.setProperties(new Properties(new TimeSeries[]{new TimeSeries(new DataSet(new Instant(new Details(temperature))))}));
        ResponseEntity<WeatherFromYrResponse> entity = new ResponseEntity<>(weatherResponse, HttpStatus.OK);
        when(generator.generateUrl(0.0, 0.0)).thenReturn(url);
        when(cachingService.checkCache(url)).thenReturn(cacheResponse);
        when(client.getWeather(url)).thenReturn(entity);
        WeatherResponse expected = new WeatherResponse();
        expected.setTemperature(temperature);
        WeatherResponse actual = infoService.getWeather(0.0, 0.0);
        assertEquals(expected, actual);
    }

    /*
     * scenario3
     * input: "0.0", "0.0"
     * expected output: "MappingFailedException"
     */
    @Test
    void scenario3() {
        String url = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=0&lon=0";
        double temperature = 20.0;
        CachingServiceResponse cacheResponse = new CachingServiceResponse();
        cacheResponse.setCacheValid(false);
        ResponseEntity<WeatherFromYrResponse> entity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        when(generator.generateUrl(0.0, 0.0)).thenReturn(url);
        when(cachingService.checkCache(url)).thenReturn(cacheResponse);
        when(client.getWeather(url)).thenReturn(entity);
        assertThrows(MappingFailedException.class, () -> {
            infoService.getWeather(0.0, 0.0);
        });
    }

}