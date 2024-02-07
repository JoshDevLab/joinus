package com.josh.joinus.storage.db.core.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @CreatedDate
    private LocalDateTime registeredDateTime;

    @LastModifiedDate
    private LocalDateTime modifiedDateTime;
}
