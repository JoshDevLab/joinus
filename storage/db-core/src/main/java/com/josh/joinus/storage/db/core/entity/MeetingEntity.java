package com.josh.joinus.storage.db.core.entity;

import com.josh.joinus.core.domain.*;
import com.josh.joinus.core.domain.meeting.Meeting;
import com.josh.joinus.core.domain.meeting.MeetingCreate;
import com.josh.joinus.core.domain.meeting.MeetingStatus;
import com.josh.joinus.core.domain.meeting.MeetingType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "meeting")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
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
    @Builder.Default
    private List<MeetingTechEntity> meetingTechEntityList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "meetingEntity",
            fetch = FetchType.LAZY)
    @Builder.Default
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
                .viewCount(0)
                .expiredDateTime(meetingCreate.getExpiredDateTime())
                .build();
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
               .viewCount(viewCount)
               .expiredDateTime(expiredDateTime)
               .build();
    }


}
