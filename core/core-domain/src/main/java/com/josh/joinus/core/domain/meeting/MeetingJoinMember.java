package com.josh.joinus.core.domain.meeting;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MeetingJoinMember {
    private Long id;
    private Long userId;
    private Long meetingId;
    private JoinStatus joinStatus;
}
