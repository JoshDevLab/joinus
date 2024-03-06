package com.josh.joinus.core.domain.meeting;

import java.util.List;

public interface MeetingJoinMemberRepository {
    boolean findByMeetingTypeAndJoinUserId(MeetingType meetingType, Long joinUserId);
    Long registerRequest(Long meetingId, Long joinUserId);
    List<MeetingJoinMember> findByMeetingId(Long meetingId);
    MeetingJoinMember findById(Long id);
}
