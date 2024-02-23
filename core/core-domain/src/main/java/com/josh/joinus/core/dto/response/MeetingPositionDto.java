package com.josh.joinus.core.dto.response;

import com.josh.joinus.core.domain.Tech;
import lombok.Getter;

@Getter
public class MeetingPositionDto {
    private Long meetingId;
    private String positionName;

    public MeetingPositionDto(Long meetingId, String positionName) {
        this.meetingId = meetingId;
        this.positionName = positionName;
    }

}
