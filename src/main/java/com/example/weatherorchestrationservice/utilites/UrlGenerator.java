package com.example.weatherorchestrationservice.utilites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlGenerator {

    @Value(("${url.weather}"))
    private String yrUrlBase;

    public String generateUrl(double lat, double lon) {
        StringBuilder sb = new StringBuilder();
        String latitude = String.valueOf(lat);
        String longitude = String.valueOf(lon);
        sb.append(yrUrlBase)
                .append("lat=")
                .append(latitude)
                .append("&lon=")
                .append(longitude);
        return sb.toString();
    }

}
