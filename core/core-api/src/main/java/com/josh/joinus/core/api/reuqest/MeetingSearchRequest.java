package com.josh.joinus.core.api.reuqest;

import com.josh.joinus.core.domain.ProcessWay;
import com.josh.joinus.core.domain.meeting.MeetingStatus;
import com.josh.joinus.core.domain.meeting.MeetingType;
import com.josh.joinus.core.dto.request.MeetingSearchCondition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class MeetingSearchRequest {
    private MeetingType meetingType;
    private List<Long> techIdList;
    private Long positionId;
    private ProcessWay processWay;
    private MeetingStatus meetingStatus;


    public MeetingSearchCondition toServiceDto() {
        return MeetingSearchCondition.builder()
                .meetingType(this.meetingType)
                .techIdList(this.techIdList)
                .positionId(this.positionId)
                .processWay(this.processWay)
                .meetingStatus(this.meetingStatus)
                .build();
    }
}
