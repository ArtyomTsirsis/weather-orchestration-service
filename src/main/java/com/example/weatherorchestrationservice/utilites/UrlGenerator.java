package com.example.weatherorchestrationservice.utilites;

import com.example.weatherorchestrationservice.dto.WeatherRequest;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UrlGenerator {

//    @Value(("${url.weather}"))
    private String yrUrlBase = "https://api.met.no/weatherapi/locationforecast/2.0/compact?";

    public String generateUrl(WeatherRequest request) {
        StringBuilder sb = new StringBuilder();
        String latitude = String.valueOf(request.getLatitude());
        String longitude = String.valueOf(request.getLongitude());
        sb.append(yrUrlBase)
                .append("lat=")
                .append(latitude)
                .append("&lon=")
                .append(longitude);
        return sb.toString();
    }

}
