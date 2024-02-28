package com.josh.joinus.core.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MeetingResponse {
    private Long meetingId;
    private String meetingName;
    private List<MeetingTechDto> techList;
    private List<String> positionList;
    private LocalDateTime expiredDate;
    private String userNickname;
    private int views;
    private int reviewCount;

    @Builder
    public MeetingResponse(Long meetingId, String meetingName, List<MeetingTechDto> techList, List<String> positionList,
                           LocalDateTime expiredDate, String userNickname, int views, int reviewCount)
    {
        this.meetingId = meetingId;
        this.meetingName = meetingName;
        this.techList = techList;
        this.positionList = positionList;
        this.expiredDate = expiredDate;
        this.userNickname = userNickname;
        this.views = views;
        this.reviewCount = reviewCount;
    }
}
