package com.josh.joinus.core.domain.meeting;

import com.josh.joinus.core.domain.Base;
import lombok.Getter;

@Getter
public class MeetingComment extends Base {
    private Long id;
    private Long meetingId;
    private Long userId;
    private String content;
}
