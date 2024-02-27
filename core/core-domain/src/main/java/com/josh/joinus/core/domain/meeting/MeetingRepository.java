package com.josh.joinus.core.domain.meeting;

import com.josh.joinus.core.dto.request.MeetingSearchCondition;
import com.josh.joinus.core.dto.response.MeetingPositionDto;
import com.josh.joinus.core.dto.response.MeetingTechDto;

import java.util.List;

public interface MeetingRepository {
    Meeting create(MeetingCreate meetingCreate);
    List<Meeting> searchByCondition(MeetingSearchCondition meetingSearchCondition);
    List<MeetingTechDto> findByMeetingTechByMeetingIds(List<Long> meetingIds);
    List<MeetingPositionDto> findByMeetingPositionByMeetingIds(List<Long> meetingIds);
    boolean duplicateLeaderUser(Long leaderUserId, MeetingType meetingType);
}
