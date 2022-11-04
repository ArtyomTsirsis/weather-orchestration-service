package com.example.weatherorchestrationservice.controller;

import com.example.weatherorchestrationservice.core.WeatherHandler;
import com.example.weatherorchestrationservice.dto.WeatherResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {

    @Mock
    private WeatherHandler handler;
    @InjectMocks
    private WeatherController controller;

    /*
     * scenario1
     * input: "24.5", "57.9"
     * expected output: "20.0"
     */
    @Test
    void scenario1() {
        double lat = 24.5;
        double lon = 57.9;
        WeatherResponse expected = new WeatherResponse(20.0);
        when(handler.getWeather(lat, lon)).thenReturn(expected);
        WeatherResponse actual = controller.getWeather(lat, lon);
        assertEquals(expected, actual);
    }

}