package com.josh.joinus.storage.db.core.entity;

import com.josh.joinus.core.domain.*;
import com.josh.joinus.storage.db.core.PositionConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

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

    @Enumerated(EnumType.STRING)
    @Convert(converter = PositionConverter.class)
    @Lob
    private EnumSet<Position> positions;

    private LocalDateTime startDateTime;

    private int headCount;

    private LocalDateTime expiredDateTime;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "meetingEntity",
                fetch = FetchType.LAZY)
    private List<MeetingTechEntity> meetingTechEntityList = new ArrayList<>();

    public void addMeetingTechEntity(MeetingTechEntity meetingTechEntity) {
        this.meetingTechEntityList.add(meetingTechEntity);
    }

    public static MeetingEntity create(MeetingCreate meetingCreate) {
        return MeetingEntity.builder()
                .leaderUserId(meetingCreate.getLeaderUserId())
                .meetingName(meetingCreate.getMeetingName())
                .meetingType(meetingCreate.getMeetingType())
                .processWay(meetingCreate.getProcessWay())
                .meetingStatus(meetingCreate.getMeetingStatus())
                .positions(meetingCreate.getPositions())
                .startDateTime(meetingCreate.getStartDateTime())
                .headCount(meetingCreate.getHeadCount())
                .expiredDateTime(meetingCreate.getExpiredDateTime())
                .build();
    }

    @Builder
    public MeetingEntity(Long leaderUserId, String meetingName, MeetingType meetingType,
                         ProcessWay processWay, MeetingStatus meetingStatus,
                         LocalDateTime startDateTime, int headCount, LocalDateTime expiredDateTime,
                         EnumSet<Position> positions)
    {
        this.leaderUserId = leaderUserId;
        this.meetingName = meetingName;
        this.meetingType = meetingType;
        this.processWay = processWay;
        this.meetingStatus = meetingStatus;
        this.positions = positions;
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
               .positions(positions)
               .startDateTime(startDateTime)
               .headCount(headCount)
               .expiredDateTime(expiredDateTime)
               .build();
    }

    public Meeting toDomainBySearch() {
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
                .techNames(meetingTechEntityList.stream().map(
                        meetingTechEntity -> meetingTechEntity.getTechEntity().getName()
                        ).collect(Collectors.toList()))
                .positions(positions)
                .build();
    }
}
