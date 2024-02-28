package com.josh.joinus.core.domain.meeting;

import com.josh.joinus.core.domain.Base;
import com.josh.joinus.core.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MeetingComment extends Base {
    private Long id;
    private Meeting meeting;
    private User user;
    private String content;

    @Builder
    public MeetingComment(Long id, Meeting meeting, User user, String content) {
        this.id = id;
        this.meeting = meeting;
        this.user = user;
        this.content = content;
    }
}
