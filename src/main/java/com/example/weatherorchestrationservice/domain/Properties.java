package com.example.weatherorchestrationservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Properties {

    @JsonProperty("timeseries")
    private TimeSeries[] timeSeries;

}
