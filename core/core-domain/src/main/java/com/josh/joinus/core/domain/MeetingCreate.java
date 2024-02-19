package com.josh.joinus.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Getter
@Setter
public class MeetingCreate {
    private Long leaderUserId;
    private String meetingName;
    private MeetingType meetingType;
    private List<Long> techIdList = new ArrayList<>();
    private ProcessWay processWay;
    private MeetingStatus meetingStatus;
    private LocalDateTime startDateTime;
    private int headCount;
    private LocalDateTime expiredDateTime;
    private EnumSet<Position> positions;

    @Builder
    private MeetingCreate(Long leaderUserId, String meetingName, MeetingType meetingType, ProcessWay processWay,
                         MeetingStatus meetingStatus, LocalDateTime startDateTime,
                         int headCount, LocalDateTime expiredDateTime, EnumSet<Position> positions)
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
}
