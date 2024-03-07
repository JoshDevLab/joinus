package com.josh.joinus.core.domain.meeting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingJoinMemberWriter {
    private final MeetingJoinMemberRepository meetingJoinMemberRepository;

    public MeetingJoinMember registerRequest(Long meetingId, Long joinUserId) {
        return meetingJoinMemberRepository.registerRequest(meetingId, joinUserId);
    }

    public int updateAccept(Long meetingJoinMemberId) {
        return meetingJoinMemberRepository.updateAccept(meetingJoinMemberId);
    }
}
