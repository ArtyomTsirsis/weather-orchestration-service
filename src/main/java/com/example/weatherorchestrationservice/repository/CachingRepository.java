package com.example.weatherorchestrationservice.repository;

import com.example.weatherorchestrationservice.domain.CachedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CachingRepository extends JpaRepository<CachedEntity, String> {

    @Modifying
    @Transactional
    @Query("UPDATE CachedEntity u SET temperature = :#{#e.temperature}, creationTime = :#{#e.creationTime} WHERE url = :#{#e.url}")
    void updateEntity(@Param("e") CachedEntity entity);

}
