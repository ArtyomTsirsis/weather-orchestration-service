package com.example.weatherorchestrationservice.repository;

import com.example.weatherorchestrationservice.domain.CachedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CachingRepository extends JpaRepository<CachedEntity, String> {

}
