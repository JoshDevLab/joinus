package com.josh.joinus.core.domain.meeting;

import java.util.List;

public interface MeetingPositionRepository {
    void create(Long meetingId, List<Long> positionList);
}
