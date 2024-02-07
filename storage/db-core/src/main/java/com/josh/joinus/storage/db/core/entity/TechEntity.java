package com.josh.joinus.storage.db.core.entity;

import com.josh.joinus.core.domain.Tech;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tech")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TechEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public static TechEntity create(String name) {
        return TechEntity.builder()
                .name(name)
                .build();
    }

    @Builder
    private TechEntity(String name) {
        this.name = name;
    }

    public Tech toDomain() {
        return Tech.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
