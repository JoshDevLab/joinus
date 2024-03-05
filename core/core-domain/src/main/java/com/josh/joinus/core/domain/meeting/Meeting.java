package com.josh.joinus.core.domain.meeting;

import com.josh.joinus.core.domain.Base;
import com.josh.joinus.core.domain.Position;
import com.josh.joinus.core.domain.ProcessWay;
import com.josh.joinus.core.domain.Tech;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private int commentCount;
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

    public void joinValidate() {
        if (isMeetingEnded()) {
            throw new IllegalArgumentException("마감된 모임입니다.");
        }

        if (isHeadCountFull()) {
            throw new IllegalArgumentException("모집인원이 다 찼습니다.");
        }
    }

    private boolean isMeetingEnded() {
        return meetingStatus.equals(MeetingStatus.END);
    }

    private boolean isHeadCountFull() {
        return headCount == 0;
    }

    public void reduceHeadCount() {
        this.headCount -= 1;
    }
}
