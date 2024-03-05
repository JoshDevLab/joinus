package com.josh.joinus.core.domain.meeting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingJoinMemberWriter {
    private final MeetingJoinMemberRepository meetingJoinMemberRepository;

    public Long register(Long meetingId, Long joinUserId) {
        return meetingJoinMemberRepository.register(meetingId, joinUserId);
    }
}
