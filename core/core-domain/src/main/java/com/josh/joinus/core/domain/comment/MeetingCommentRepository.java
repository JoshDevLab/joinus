package com.josh.joinus.core.domain.comment;

import java.util.List;

public interface MeetingCommentRepository {
    List<MeetingComment> findByMeetingId(Long meetingId);
    MeetingComment create(MeetingCommentCreate meetingCommentCreate);
}
