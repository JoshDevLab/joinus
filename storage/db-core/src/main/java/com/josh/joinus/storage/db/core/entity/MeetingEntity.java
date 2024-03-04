package com.josh.joinus.storage.db.core.entity;

import com.josh.joinus.core.domain.*;
import com.josh.joinus.core.domain.meeting.Meeting;
import com.josh.joinus.core.domain.meeting.MeetingCreate;
import com.josh.joinus.core.domain.meeting.MeetingStatus;
import com.josh.joinus.core.domain.meeting.MeetingType;
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

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private MeetingType meetingType;

    @Enumerated(EnumType.STRING)
    private ProcessWay processWay;

    @Enumerated(EnumType.STRING)
    private MeetingStatus meetingStatus;

    private LocalDateTime startDateTime;

    private int headCount;

    private int viewCount;

    private LocalDateTime expiredDateTime;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "meetingEntity",
                fetch = FetchType.LAZY)
    private List<MeetingTechEntity> meetingTechEntityList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "meetingEntity",
            fetch = FetchType.LAZY)
    private List<MeetingPositionEntity> meetingPositionEntityList  = new ArrayList<>();

    public void addMeetingTechEntity(MeetingTechEntity meetingTechEntity) {
        this.meetingTechEntityList.add(meetingTechEntity);
    }

    public void addMeetingPositionEntity(MeetingPositionEntity meetingPositionEntity) {
        this.meetingPositionEntityList.add(meetingPositionEntity);
    }

    public static MeetingEntity create(MeetingCreate meetingCreate) {
        return MeetingEntity.builder()
                .leaderUserId(meetingCreate.getLeaderUserId())
                .meetingName(meetingCreate.getMeetingName())
                .content(meetingCreate.getContent())
                .meetingType(meetingCreate.getMeetingType())
                .processWay(meetingCreate.getProcessWay())
                .meetingStatus(meetingCreate.getMeetingStatus())
                .startDateTime(meetingCreate.getStartDateTime())
                .headCount(meetingCreate.getHeadCount())
                .viewCount(meetingCreate.getViewCount())
                .expiredDateTime(meetingCreate.getExpiredDateTime())
                .build();
    }

    @Builder
    public MeetingEntity(Long leaderUserId, String meetingName, String content, MeetingType meetingType,
                         ProcessWay processWay, MeetingStatus meetingStatus,
                         LocalDateTime startDateTime, int headCount, int viewCount,
                         LocalDateTime expiredDateTime)
    {
        this.leaderUserId = leaderUserId;
        this.meetingName = meetingName;
        this.content = content;
        this.meetingType = meetingType;
        this.processWay = processWay;
        this.meetingStatus = meetingStatus;
        this.startDateTime = startDateTime;
        this.headCount = headCount;
        this.viewCount = viewCount;
        this.expiredDateTime = expiredDateTime;
    }

    public Meeting toDomain() {
       return Meeting.builder()
               .id(id)
               .leaderUserId(leaderUserId)
               .meetingName(meetingName)
               .content(content)
               .meetingType(meetingType)
               .processWay(processWay)
               .meetingStatus(meetingStatus)
               .startDateTime(startDateTime)
               .headCount(headCount)
               .expiredDateTime(expiredDateTime)
               .build();
    }


}
