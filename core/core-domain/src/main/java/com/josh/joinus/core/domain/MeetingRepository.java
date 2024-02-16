package com.josh.joinus.core.domain;

import com.josh.joinus.core.dto.request.MeetingSearchCondition;

import java.util.List;

public interface MeetingRepository {
    Meeting create(MeetingCreate meetingCreate);
    List<Meeting> searchByCondition(MeetingSearchCondition meetingSearchCondition);
}
