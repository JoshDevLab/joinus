package com.josh.joinus.storage.db.core.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "meeting_tech")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingTechEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private MeetingEntity meetingEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_id")
    private TechEntity techEntity;

    public static MeetingTechEntity create(MeetingEntity meetingEntity, TechEntity techEntity) {
        return MeetingTechEntity.builder()
                .meetingEntity(meetingEntity)
                .techEntity(techEntity)
                .build();
    }

    @Builder
    private MeetingTechEntity(MeetingEntity meetingEntity, TechEntity techEntity) {
        this.meetingEntity = meetingEntity;
        this.techEntity = techEntity;
    }
}
