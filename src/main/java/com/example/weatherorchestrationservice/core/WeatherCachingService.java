package com.example.weatherorchestrationservice.core;

import com.example.weatherorchestrationservice.domain.CachedEntity;
import com.example.weatherorchestrationservice.dto.CachingServiceResponse;
import com.example.weatherorchestrationservice.repository.CachingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WeatherCachingService {

    private final CachingRepository repository;

    public CachingServiceResponse checkCache(String url) {
        CachingServiceResponse response = new CachingServiceResponse();
        repository.findById(url)
                .filter(cached -> cached.getCreationTime().isAfter(LocalDateTime.now().minusHours(1)))
                .ifPresentOrElse(cached -> setCacheValidResponse(response, cached.getTemperature()),
                () -> response.setCacheValid(false));
        return response;
    }

    public void saveOrUpdate(String url, double temperature) {
        CachedEntity entity = new CachedEntity(url, temperature, LocalDateTime.now());
        repository.findById(url).ifPresentOrElse(o -> repository.updateEntity(entity), () -> repository.save(entity));
    }

    private void setCacheValidResponse(CachingServiceResponse response, double temperature) {
        response.setCacheValid(true);
        response.setTemperature(temperature);
    }

    @Scheduled(fixedRate = 108000000)
    void cleanExpiredCache() {
        repository.findByCreationTimeBefore(LocalDateTime.now().minusHours(1)).forEach(repository::delete);
    }

}
