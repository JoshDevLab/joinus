package com.josh.joinus.core.domain.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeetingCommentService {

    private final MeetingCommentWriter meetingCommentWriter;

    @Transactional
    public MeetingComment create(MeetingCommentCreate meetingCommentCreate) {
        return meetingCommentWriter.create(meetingCommentCreate);
    }
}
