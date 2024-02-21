package com.josh.joinus.storage.db.core.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "position")
public class PositionEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


}
