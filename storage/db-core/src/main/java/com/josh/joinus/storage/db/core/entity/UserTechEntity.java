package com.josh.joinus.storage.db.core.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_tech")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
