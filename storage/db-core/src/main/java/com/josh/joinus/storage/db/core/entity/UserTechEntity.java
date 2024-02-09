package com.josh.joinus.storage.db.core.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "user_tech")
public class UserTechEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long techId;

    @Builder
    private UserTechEntity(Long userId, Long techId) {
        this.userId = userId;
        this.techId = techId;
    }
}
