package com.josh.joinus.core.dto.request;

import com.josh.joinus.core.domain.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MeetingSearchCondition {
    private MeetingType meetingType;
    private List<Long> techIdList;
    private Long positionId;
    private ProcessWay processWay;
    private MeetingStatus meetingStatus;

    @Builder
    private MeetingSearchCondition(MeetingType meetingType, List<Long> techIdList, Long positionId,
                                   ProcessWay processWay, MeetingStatus meetingStatus)
    {
        this.meetingType = meetingType;
        this.techIdList = techIdList;
        this.positionId = positionId;
        this.processWay = processWay;
        this.meetingStatus = meetingStatus;
    }
}
