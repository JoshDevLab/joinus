package com.josh.joinus.core.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingCommentCreate {
    private String content;
    private Long meetingId;
    private Long userId;
}
