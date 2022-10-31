package com.example.weatherorchestrationservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Details {

    @JsonProperty("air_temperature")
    private double temperature;

}
