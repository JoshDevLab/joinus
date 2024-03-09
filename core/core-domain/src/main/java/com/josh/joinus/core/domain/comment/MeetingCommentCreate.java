package com.josh.joinus.core.domain.comment;

import lombok.Getter;

@Getter
public class MeetingCommentCreate {
    private String content;
    private Long meetingId;
    private Long userId;
}
