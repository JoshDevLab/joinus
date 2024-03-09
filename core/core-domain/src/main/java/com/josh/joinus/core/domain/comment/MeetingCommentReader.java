package com.josh.joinus.core.domain.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingCommentReader {
    private final MeetingCommentRepository meetingCommentRepository;
}
