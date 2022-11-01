package com.example.weatherorchestrationservice.core;

import com.example.weatherorchestrationservice.domain.CachedEntity;
import com.example.weatherorchestrationservice.dto.CachingServiceResponse;
import com.example.weatherorchestrationservice.repository.CachingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherCachingServiceTest {

    @Mock
    private CachingRepository repository;
    @InjectMocks
    private WeatherCachingService service;

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

    /*
     * scenario2
     * input: "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=0&lon=0"
     * expected output: "false", "0"
     */
    @Test
    void scenario2() {
        String url = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=0&lon=0";
        CachedEntity entity = new CachedEntity(url, 20.0, LocalDateTime.now().minusHours(2));
        CachingServiceResponse expected = new CachingServiceResponse();
        expected.setCacheValid(false);
        when(repository.findById(url)).thenReturn(Optional.of(entity));
        CachingServiceResponse actual = service.checkCache(url);
        assertEquals(expected, actual);
    }

    /*
     * scenario3
     * input: "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=0&lon=0"
     * expected output: "false", "0"
     */
    @Test
    void scenario3() {
        String url = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=0&lon=0";
        CachingServiceResponse expected = new CachingServiceResponse();
        expected.setCacheValid(false);
        when(repository.findById(url)).thenReturn(Optional.empty());
        CachingServiceResponse actual = service.checkCache(url);
        assertEquals(expected, actual);
    }

    /*
     * scenario4
     * input: "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=0&lon=0", "20.0", "Present"
     * expected output: "update"
     */
    @Test
    void scenario4() {
        String url = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=0&lon=0";
        double temperature = 20.0;
        CachedEntity entity = new CachedEntity(url, temperature, LocalDateTime.now());
        when(repository.findById(url)).thenReturn(Optional.of(entity));
        service.saveOrUpdate(url, temperature);
        verify(repository).updateEntity(entity);
    }

    /*
     * scenario5
     * input: "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=0&lon=0", "20.0", "Empty"
     * expected output: "save"
     */
    @Test
    void scenario5() {
        String url = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=0&lon=0";
        double temperature = 20.0;
        CachedEntity entity = new CachedEntity(url, temperature, LocalDateTime.now());
        when(repository.findById(url)).thenReturn(Optional.empty());
        service.saveOrUpdate(url, temperature);
        verify(repository).save(entity);
    }

}