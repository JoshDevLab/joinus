package com.josh.joinus.core.domain.meeting;

import java.util.List;

public interface MeetingCommentRepository {
    List<MeetingComment> findByMeetingId(Long meetingId);

}
