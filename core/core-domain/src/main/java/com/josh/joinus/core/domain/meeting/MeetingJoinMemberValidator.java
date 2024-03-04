package com.josh.joinus.core.domain.meeting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingJoinMemberValidator {
    private final MeetingJoinMemberRepository meetingJoinMemberRepository;

    public void duplicateValidation(MeetingType meetingType, Long joinUserId) {
       if (meetingJoinMemberRepository.findByMeetingTypeAndJoinUserId(meetingType, joinUserId)) {
           throw new IllegalArgumentException("이미 참여중인 " + meetingType + "가 있습니다.");
       }
    }
}
