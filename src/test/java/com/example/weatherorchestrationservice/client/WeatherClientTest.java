package com.example.weatherorchestrationservice.client;

import com.example.weatherorchestrationservice.domain.*;
import com.example.weatherorchestrationservice.dto.WeatherFromYrResponse;
import com.example.weatherorchestrationservice.exception.RequestFailedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherClientTest {

    @Mock
    private HttpClient httpClient;
    @InjectMocks
    private WeatherClient client;

    /*
     * scenario1
     * input: "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=25.25&lon=-35.35"
     * expected output: ResponseEntity<WeatherFromYrResponse>
     */
    @Test
    void scenario1() {
        double temperature = 20.0;
        String url = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=25.25&lon=-35.35";
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", null);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        WeatherFromYrResponse weatherResponse = new WeatherFromYrResponse();
        weatherResponse.setProperties(new Properties(new TimeSeries[]{new TimeSeries(new DataSet(new Instant(new Details(temperature))))}));
        ResponseEntity<WeatherFromYrResponse> expected = new ResponseEntity<>(weatherResponse, HttpStatus.OK);
        when(httpClient.exchange(url, HttpMethod.GET, entity , WeatherFromYrResponse.class)).thenReturn(expected);
        ResponseEntity<WeatherFromYrResponse> actual = client.getWeather(url);
        assertEquals(expected, actual);
    }

    /*
     * scenario2
     * input: "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=25.25&lon=-35.35"
     * expected output: "RequestFailedException"
     */
    @Test
    void scenario2() {
        double temperature = 20.0;
        String url = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=25.25&lon=-35.35";
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", null);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        when(httpClient.exchange(url, HttpMethod.GET, entity , WeatherFromYrResponse.class)).thenThrow(HttpClientErrorException.class);
        assertThrows(RequestFailedException.class, () -> {
            client.getWeather(url);
        });
    }

}