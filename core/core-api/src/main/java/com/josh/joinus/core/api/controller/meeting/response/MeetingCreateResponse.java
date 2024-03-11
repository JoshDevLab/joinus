package com.josh.joinus.core.api.controller.meeting.response;

import com.josh.joinus.core.domain.ProcessWay;
import com.josh.joinus.core.domain.meeting.Meeting;
import com.josh.joinus.core.domain.meeting.MeetingStatus;
import com.josh.joinus.core.domain.meeting.MeetingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class MeetingCreateResponse {
    private Long id;
    private Long leaderUserId;
    private String meetingName;
    private String content;
    private MeetingType meetingType;
    private ProcessWay processWay;
    private MeetingStatus meetingStatus;
    private LocalDateTime startDateTime;
    private LocalDateTime expiredDateTime;
    private int headCount;

    public static MeetingCreateResponse of(Meeting meeting) {
       return MeetingCreateResponse.builder()
                .id(meeting.getId())
                .leaderUserId(meeting.getLeaderUserId())
                .meetingName(meeting.getMeetingName())
                .content(meeting.getContent())
                .meetingType(meeting.getMeetingType())
                .processWay(meeting.getProcessWay())
                .meetingStatus(meeting.getMeetingStatus())
                .startDateTime(meeting.getStartDateTime())
                .expiredDateTime(meeting.getExpiredDateTime())
                .headCount(meeting.getHeadCount())
                .build();
    }
}
