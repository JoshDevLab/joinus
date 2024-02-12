package com.josh.joinus.core.domain;

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
}
