package com.josh.joinus.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Getter
@Builder
public class Meeting extends Base {
    private Long id;
    private Long leaderUserId;
    private String meetingName;
    private MeetingType meetingType;
    private List<String> techNames;
    private ProcessWay processWay;
    private MeetingStatus meetingStatus;
    private LocalDateTime startDateTime;
    private int headCount;
    private LocalDateTime expiredDateTime;
    private EnumSet<Position> positions;
}
