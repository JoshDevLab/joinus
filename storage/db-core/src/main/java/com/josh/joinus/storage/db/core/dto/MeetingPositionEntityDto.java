package com.josh.joinus.storage.db.core.dto;

import com.josh.joinus.core.dto.response.MeetingPositionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MeetingPositionEntityDto {
    private Long meetingId;
    private String positionName;

    public MeetingPositionDto toDomain() {
        return new MeetingPositionDto(this.meetingId, this.positionName);
    }
}
