package com.josh.joinus.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Meeting extends Base {
    private Long id;
    private Long leaderUserId;
    private String meetingName;
    private MeetingType meetingType;
    private List<Tech> techList = new ArrayList<>();
    private ProcessWay processWay;
    private MeetingStatus meetingStatus;
    private LocalDateTime startDateTime;
    private int headCount;
    private LocalDateTime expiredDateTime;
    private List<Position> positionList = new ArrayList<>();

    @Builder
    private Meeting(Long id, Long leaderUserId, String meetingName, MeetingType meetingType,
                   ProcessWay processWay, MeetingStatus meetingStatus, LocalDateTime startDateTime,
                   int headCount, LocalDateTime expiredDateTime)
    {
        this.id = id;
        this.leaderUserId = leaderUserId;
        this.meetingName = meetingName;
        this.meetingType = meetingType;
        this.processWay = processWay;
        this.meetingStatus = meetingStatus;
        this.startDateTime = startDateTime;
        this.headCount = headCount;
        this.expiredDateTime = expiredDateTime;
    }
}
