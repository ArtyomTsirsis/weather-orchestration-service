package com.example.weatherorchestrationservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Properties {

    @JsonProperty("timeseries")
    private TimeSeries[] timeSeries;

}
