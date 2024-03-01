package com.josh.joinus.core.domain.meeting;

import com.josh.joinus.core.domain.Position;

import java.util.List;

public interface MeetingPositionRepository {
    void create(Long meetingId, List<Long> positionList);

    List<Position> findByMeetingId(Long meetingId);
}
