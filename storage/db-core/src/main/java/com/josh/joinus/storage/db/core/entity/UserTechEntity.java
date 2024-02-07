package com.josh.joinus.storage.db.core.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_tech")
public class UserTechEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long techId;
}
