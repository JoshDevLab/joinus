package com.josh.joinus.core.domain.meeting;

import com.josh.joinus.core.domain.Base;
import com.josh.joinus.core.domain.Position;
import com.josh.joinus.core.domain.ProcessWay;
import com.josh.joinus.core.domain.Tech;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

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
    private int viewCount;
    private LocalDateTime expiredDateTime;
    private List<Tech> techList;
    private List<Position> positionList;

    public boolean incrementViewCount(Long accessUserId) {
        if (!this.leaderUserId.equals(accessUserId)) {
            this.viewCount += 1;
            return true;
        }
        return false;
    }
}
