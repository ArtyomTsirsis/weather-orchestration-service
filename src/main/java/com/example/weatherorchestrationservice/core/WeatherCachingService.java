package com.example.weatherorchestrationservice.core;

import com.example.weatherorchestrationservice.domain.CachedEntity;
import com.example.weatherorchestrationservice.dto.CachingServiceResponse;
import com.example.weatherorchestrationservice.repository.CachingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherCachingService {

    private final CachingRepository repository;

    public CachingServiceResponse checkCache(String url) {
        CachingServiceResponse response = new CachingServiceResponse();
        Optional<CachedEntity> cached = repository.findById(url);
        if (cached.isPresent() && cached.get().getCreationTime().isAfter(LocalDateTime.now().minusHours(1))) {
            response.setCacheValid(true);
            response.setTemperature(cached.get().getTemperature());
        } else {
            response.setCacheValid(false);
        }
        return response;
    }

    public void saveOrUpdate(String url, double temperature) {
        CachedEntity entity = new CachedEntity(url, temperature, LocalDateTime.now());
        if (repository.findById(url).isEmpty()) {
            repository.save(entity);
        } else {
            repository.updateEntity(entity);
        }
    }

}
