package com.josh.joinus.core.dto.response;

import com.josh.joinus.core.domain.Tech;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class MeetingTechDto {
    private Long meetingId;
    private String techName;
    private String techImg;

    public MeetingTechDto(Long meetingId, String techName, String techImg) {
        this.meetingId = meetingId;
        this.techName = techName;
        this.techImg = techImg;
    }

    @Builder
    public MeetingTechDto(String techName, String techImg) {
        this.techName = techName;
        this.techImg = techImg;
    }

}