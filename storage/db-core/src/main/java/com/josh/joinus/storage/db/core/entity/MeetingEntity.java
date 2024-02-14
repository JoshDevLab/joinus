package com.josh.joinus.storage.db.core.entity;

import com.josh.joinus.core.domain.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "meeting")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MeetingEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long leaderUserId;
    private String meetingName;
    @Enumerated(EnumType.STRING)
    private MeetingType meetingType;
    @Enumerated(EnumType.STRING)
    private ProcessWay processWay;
    @Enumerated(EnumType.STRING)
    private MeetingStatus meetingStatus;
    private LocalDateTime startDateTime;
    private int headCount;
    private LocalDateTime expiredDateTime;

    public static MeetingEntity create(MeetingCreate meetingCreate) {
        return MeetingEntity.builder()
                .leaderUserId(meetingCreate.getLeaderUserId())
                .meetingName(meetingCreate.getMeetingName())
                .meetingType(meetingCreate.getMeetingType())
                .processWay(meetingCreate.getProcessWay())
                .meetingStatus(meetingCreate.getMeetingStatus())
                .startDateTime(meetingCreate.getStartDateTime())
                .headCount(meetingCreate.getHeadCount())
                .expiredDateTime(meetingCreate.getExpiredDateTime())
                .build();
    }

    @Builder
    public MeetingEntity(Long leaderUserId, String meetingName, MeetingType meetingType,
                         ProcessWay processWay, MeetingStatus meetingStatus,
                         LocalDateTime startDateTime, int headCount, LocalDateTime expiredDateTime)
    {
        this.leaderUserId = leaderUserId;
        this.meetingName = meetingName;
        this.meetingType = meetingType;
        this.processWay = processWay;
        this.meetingStatus = meetingStatus;
        this.startDateTime = startDateTime;
        this.headCount = headCount;
        this.expiredDateTime = expiredDateTime;
    }

    public Meeting toDomain() {
       return Meeting.builder()
               .id(id)
               .leaderUserId(leaderUserId)
               .meetingName(meetingName)
               .meetingType(meetingType)
               .processWay(processWay)
               .meetingStatus(meetingStatus)
               .startDateTime(startDateTime)
               .headCount(headCount)
               .expiredDateTime(expiredDateTime)
               .build();
    }
}
