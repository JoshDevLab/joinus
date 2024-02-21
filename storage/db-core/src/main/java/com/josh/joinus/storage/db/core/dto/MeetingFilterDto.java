package com.josh.joinus.storage.db.core.dto;

import com.josh.joinus.core.domain.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

@Getter
@Setter
public class MeetingFilterDto {
    private Long id;
    private Long leaderUserId;
    private String meetingName;
    private MeetingType meetingType;
    private List<Tech> techNames;
    private ProcessWay processWay;
    private MeetingStatus meetingStatus;
    private LocalDateTime startDateTime;
    private int headCount;
    private LocalDateTime expiredDateTime;
    private List<String> positionName;
}
