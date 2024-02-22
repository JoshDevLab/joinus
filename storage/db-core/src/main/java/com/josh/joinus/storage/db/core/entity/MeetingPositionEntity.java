package com.josh.joinus.storage.db.core.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "meeting_position")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingPositionEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private MeetingEntity meetingEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private PositionEntity positionEntity;

    public static MeetingPositionEntity create(MeetingEntity meetingEntity, PositionEntity positionEntity) {
        return MeetingPositionEntity.builder()
                .meetingEntity(meetingEntity)
                .positionEntity(positionEntity)
                .build();
    }

    @Builder
    private MeetingPositionEntity(MeetingEntity meetingEntity, PositionEntity positionEntity) {
        this.meetingEntity = meetingEntity;
        this.positionEntity = positionEntity;
    }
}
