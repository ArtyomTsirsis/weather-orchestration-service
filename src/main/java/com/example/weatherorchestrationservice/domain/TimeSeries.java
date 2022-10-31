package com.example.weatherorchestrationservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TimeSeries {

    @JsonProperty("data")
    private DataSet dataSet;

}
