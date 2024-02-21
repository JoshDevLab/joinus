package com.josh.joinus.storage.db.core.dto;

import com.josh.joinus.core.domain.Tech;
import com.josh.joinus.storage.db.core.entity.TechEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeetingTechPositionDto {
    private Long meetingId;
    private TechEntity techEntity;
    private String positionName;

    public MeetingTechPositionDto(Long meetingId, TechEntity techEntity, String positionName) {
        this.meetingId = meetingId;
        this.techEntity = techEntity;
        this.positionName = positionName;
    }
}
