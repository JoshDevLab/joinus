package com.josh.joinus.core.domain.meeting;

import com.josh.joinus.core.domain.meeting.MeetingPositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MeetingPositionWriter {

    private final MeetingPositionRepository meetingPositionRepository;

    public void create(Long meetingId, List<Long> positionList) {
        meetingPositionRepository.create(meetingId, positionList);
    }
}
