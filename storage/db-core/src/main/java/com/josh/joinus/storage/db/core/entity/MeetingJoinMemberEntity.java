package com.josh.joinus.storage.db.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "meeting_join_member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingJoinMemberEntity {
    @Id @GeneratedValue
    private Long id;
    private Long meetingId;
    private Long userId;

    @Builder
    private MeetingJoinMemberEntity(Long id, Long meetingId, Long userId) {
        this.id = id;
        this.meetingId = meetingId;
        this.userId = userId;
    }

    public static MeetingJoinMemberEntity create(Long meetingId, Long joinUserId) {
        return MeetingJoinMemberEntity.builder()
                .meetingId(meetingId)
                .userId(joinUserId)
                .build();
    }
}
