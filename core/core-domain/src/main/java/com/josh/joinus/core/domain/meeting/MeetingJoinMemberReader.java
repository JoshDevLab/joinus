package com.josh.joinus.core.domain.meeting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingJoinMemberReader {
    private final MeetingJoinMemberRepository meetingJoinMemberRepository;

    public MeetingJoinMember findById(Long meetingJoinMemberId) {
        return meetingJoinMemberRepository.findById(meetingJoinMemberId);
    }
}
