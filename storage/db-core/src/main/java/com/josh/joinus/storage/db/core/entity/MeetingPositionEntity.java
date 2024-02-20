package com.josh.joinus.storage.db.core.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "meeting_position")
public class MeetingPositionEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private MeetingEntity meetingEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private PositionEntity positionEntity;
}
