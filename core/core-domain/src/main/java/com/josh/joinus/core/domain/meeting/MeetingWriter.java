package com.josh.joinus.core.domain.meeting;

import com.josh.joinus.core.domain.meeting.MeetingCreate;
import com.josh.joinus.core.domain.meeting.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingWriter {
    private final MeetingRepository meetingRepository;
    public Long create(MeetingCreate meetingCreate) {
        return meetingRepository.create(meetingCreate);
    }
}
