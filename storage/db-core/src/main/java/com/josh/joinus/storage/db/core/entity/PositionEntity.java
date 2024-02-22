package com.josh.joinus.storage.db.core.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "position")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PositionEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public PositionEntity(String name) {
        this.name = name;
    }
}
