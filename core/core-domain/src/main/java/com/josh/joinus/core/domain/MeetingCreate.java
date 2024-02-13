package com.josh.joinus.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private List<Position> positionList = new ArrayList<>();
}
