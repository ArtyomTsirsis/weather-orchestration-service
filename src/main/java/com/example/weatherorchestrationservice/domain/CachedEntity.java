package com.example.weatherorchestrationservice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "CachedEntity")
@Table(name = "cache")
public class CachedEntity {

    @Id
    private String url;
    @Column
    private double temperature;
    @Column
    private LocalDateTime creationTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CachedEntity that = (CachedEntity) o;
        return url != null && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
