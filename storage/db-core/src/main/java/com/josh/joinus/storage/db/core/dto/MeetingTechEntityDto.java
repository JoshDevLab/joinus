package com.josh.joinus.storage.db.core.dto;

import com.josh.joinus.core.domain.Tech;
import com.josh.joinus.core.dto.response.MeetingTechDto;
import com.josh.joinus.storage.db.core.entity.TechEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MeetingTechEntityDto {
    private Long meetingId;
    private TechEntity techEntity;

    public MeetingTechDto toDomain() {
        return new MeetingTechDto(this.meetingId, this.techEntity.toDomain());
    }
}
