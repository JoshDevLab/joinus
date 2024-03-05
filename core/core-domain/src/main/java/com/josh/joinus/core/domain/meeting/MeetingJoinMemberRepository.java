package com.josh.joinus.core.domain.meeting;

public interface MeetingJoinMemberRepository {
    boolean findByMeetingTypeAndJoinUserId(MeetingType meetingType, Long joinUserId);
}