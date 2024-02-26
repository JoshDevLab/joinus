package com.josh.joinus.core.domain.meeting;

import com.josh.joinus.core.domain.Base;
import com.josh.joinus.core.domain.MeetingStatus;
import com.josh.joinus.core.domain.MeetingType;
import com.josh.joinus.core.domain.ProcessWay;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Meeting extends Base {
    private Long id;
    private Long leaderUserId;
    private String meetingName;
    private String content;
    private MeetingType meetingType;
    private ProcessWay processWay;
    private MeetingStatus meetingStatus;
    private LocalDateTime startDateTime;
    private int headCount;
    private LocalDateTime expiredDateTime;
}
