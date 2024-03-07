package com.josh.joinus.storage.db.core.entity;

import com.josh.joinus.core.domain.meeting.JoinStatus;
import com.josh.joinus.core.domain.meeting.MeetingJoinMember;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "meeting_join_member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingJoinMemberEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long meetingId;
    private Long userId;

    @Enumerated(EnumType.STRING)
    private JoinStatus joinStatus;

    @Builder
    private MeetingJoinMemberEntity(Long meetingId, Long userId, JoinStatus joinStatus) {
        this.meetingId = meetingId;
        this.userId = userId;
        this.joinStatus = joinStatus;
    }

    public static MeetingJoinMemberEntity createRequest(Long meetingId, Long joinUserId) {
        return MeetingJoinMemberEntity.builder()
                .meetingId(meetingId)
                .userId(joinUserId)
                .joinStatus(JoinStatus.WAITING)
                .build();
    }

    public MeetingJoinMember toDomain() {
        return MeetingJoinMember
                .builder()
                .id(this.id)
                .meetingId(this.meetingId)
                .userId(this.userId)
                .joinStatus(this.joinStatus)
                .build();
    }
}
