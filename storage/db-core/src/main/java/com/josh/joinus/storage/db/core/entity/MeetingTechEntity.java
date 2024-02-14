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
    private Long meetingTechId;
    private Long meetingId;
    private Long techId;

    public static MeetingTechEntity create(Long meetingId, Long techId) {
        return MeetingTechEntity.builder()
                .meetingId(meetingId)
                .techId(techId)
                .build();
    }

    @Builder
    private MeetingTechEntity(Long meetingId, Long techId) {
        this.meetingId = meetingId;
        this.techId = techId;
    }
}
