package com.josh.joinus.core.dto.response;

import com.josh.joinus.core.domain.Tech;
import lombok.Getter;

@Getter
public class MeetingTechDto {
    private Long meetingId;
    private Tech tech;

    public MeetingTechDto(Long meetingId, Tech tech) {
        this.meetingId = meetingId;
        this.tech = tech;
    }
}
