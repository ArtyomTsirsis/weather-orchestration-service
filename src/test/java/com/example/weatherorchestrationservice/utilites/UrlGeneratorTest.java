package com.example.weatherorchestrationservice.utilites;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UrlGeneratorTest {

    private final UrlGenerator generator = new UrlGenerator("https://api.met.no/weatherapi/locationforecast/2.0/compact?");

    /*
     * scenario1
     * input: "25.25", "-35.35"
     * expected output: "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=25.25&lon=-35.35"
     */
    @Test
    void scenario1() {
        String actual = generator.generateUrl(25.25, -35.35);
        assertEquals("https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=25.25&lon=-35.35", actual);
    }

    /*
     * scenario2
     * input: "-90", "180"
     * expected output: "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=-90&lon=180"
     */
    @Test
    void scenario2() {
        String actual = generator.generateUrl(-90, 180);
        assertEquals("https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=-90.0&lon=180.0", actual);
    }

    /*
     * scenario3
     * input: "259.235", "0.244"
     * expected output: "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=259.235&lon=0.244"
     */
    @Test
    void scenario3() {
        String actual = generator.generateUrl(259.235, 0.244);
        assertEquals("https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=259.235&lon=0.244", actual);
    }

}