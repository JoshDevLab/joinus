package com.josh.joinus.core.domain.meeting;

import com.josh.joinus.core.domain.ProcessWay;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MeetingCreate {
    private Long leaderUserId;
    private String meetingName;
    private String content;
    private MeetingType meetingType;
    private List<Long> techIdList;
    private ProcessWay processWay;
    private MeetingStatus meetingStatus;
    private LocalDateTime startDateTime;
    private int headCount;
    private LocalDateTime expiredDateTime;
    private List<Long> positionList;

    @Builder
    private MeetingCreate(Long leaderUserId, String meetingName, String content,
                          MeetingType meetingType, List<Long> techIdList, ProcessWay processWay,
                          MeetingStatus meetingStatus, LocalDateTime startDateTime, int headCount,
                          LocalDateTime expiredDateTime, List<Long> positionList)
    {
        this.leaderUserId = leaderUserId;
        this.meetingName = meetingName;
        this.content = content;
        this.meetingType = meetingType;
        this.techIdList = techIdList;
        this.processWay = processWay;
        this.meetingStatus = meetingStatus;
        this.positionList = positionList;
        this.startDateTime = startDateTime;
        this.headCount = headCount;
        this.expiredDateTime = expiredDateTime;
    }
}
