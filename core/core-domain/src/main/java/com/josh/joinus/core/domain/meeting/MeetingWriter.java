package com.josh.joinus.core.domain.meeting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingWriter {
    private final MeetingRepository meetingRepository;
    public Meeting create(MeetingCreate meetingCreate) {
        return meetingRepository.create(meetingCreate);
    }

    public void updateViewCount() {
    }
}
