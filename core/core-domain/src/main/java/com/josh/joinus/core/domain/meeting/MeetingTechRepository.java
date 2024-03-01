package com.josh.joinus.core.domain.meeting;

import com.josh.joinus.core.domain.Tech;

import java.util.List;

public interface MeetingTechRepository {
    void create(Long meetingId, List<Long> techIdList);
    List<Tech> findByMeetingId(Long meetingId);
}
