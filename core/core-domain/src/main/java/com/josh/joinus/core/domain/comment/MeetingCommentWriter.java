package com.josh.joinus.core.domain.comment;

import com.josh.joinus.core.domain.User;
import com.josh.joinus.core.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@RequiredArgsConstructor
public class MeetingCommentWriter {
    private final MeetingCommentRepository meetingCommentRepository;
    private final UserRepository userRepository;

    public MeetingComment create(MeetingCommentCreate meetingCommentCreate) {
        return meetingCommentRepository.create(meetingCommentCreate);
    }
}
