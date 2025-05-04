package com.example.sellWebApp.entity.impl;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class EntityImpl {

    @Column(nullable = false)
    private String function;

    @CreationTimestamp
    private LocalDateTime createDate;

    @UpdateTimestamp
    private LocalDateTime lastUpdateDate;
    @PrePersist
    public void prePersist() {
        createDate = LocalDateTime.now();
        lastUpdateDate = LocalDateTime.now();
        function = "C";
    }

    @PreUpdate
    public void postUpdate() {

        lastUpdateDate = LocalDateTime.now();

    }
}