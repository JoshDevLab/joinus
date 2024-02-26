package com.josh.joinus.core.domain.meeting;

import java.util.List;

public interface MeetingTechRepository {
    void create(Long meetingId, List<Long> techIdList);
}
