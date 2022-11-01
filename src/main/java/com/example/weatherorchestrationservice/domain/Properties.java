package com.example.weatherorchestrationservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Properties {

    @JsonProperty("timeseries")
    private TimeSeries[] timeSeries;

}
