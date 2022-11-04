package com.example.weatherorchestrationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@PropertySource(value = {"classpath:config.properties"})
@EnableScheduling
public class WeatherOrchestrationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherOrchestrationServiceApplication.class, args);
	}

}
