package com.example.weatherorchestrationservice.exception;

public class RequestFailedException extends RuntimeException {

    public RequestFailedException(String message) {
        super(message);
    }
}
