package com.josh.joinus.storage.db.core.dto;

import com.josh.joinus.core.domain.ProcessWay;
import com.josh.joinus.core.domain.meeting.MeetingStatus;
import com.josh.joinus.core.domain.meeting.MeetingType;
import com.josh.joinus.core.domain.meeting.Meeting;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MeetingEntityDto {
    private Long id;
    private Long leaderUserId;
    private String meetingName;
    private String content;
    private MeetingType meetingType;
    private ProcessWay processWay;
    private MeetingStatus meetingStatus;
    private LocalDateTime startDateTime;
    private int headCount;
    private int viewCount;
    private Long commentCount;
    private LocalDateTime expiredDateTime;

    public MeetingEntityDto(Long id, Long leaderUserId, String meetingName, String content, MeetingType meetingType,
                            ProcessWay processWay, MeetingStatus meetingStatus, LocalDateTime startDateTime,
                            int headCount, int viewCount, Long commentCount, LocalDateTime expiredDateTime) {
        this.id = id;
        this.leaderUserId = leaderUserId;
        this.meetingName = meetingName;
        this.content = content;
        this.meetingType = meetingType;
        this.processWay = processWay;
        this.meetingStatus = meetingStatus;
        this.startDateTime = startDateTime;
        this.headCount = headCount;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.expiredDateTime = expiredDateTime;
    }

    public Meeting toDomain() {
        return Meeting.builder()
                .id(getId())
                .leaderUserId(getLeaderUserId())
                .meetingName(getMeetingName())
                .content(getContent())
                .meetingType(getMeetingType())
                .processWay(getProcessWay())
                .meetingStatus(getMeetingStatus())
                .startDateTime(getStartDateTime())
                .headCount(getHeadCount())
                .viewCount(getViewCount())
                .expiredDateTime(getExpiredDateTime())
                .commentCount(this.commentCount.intValue())
                .build();
    }
}
