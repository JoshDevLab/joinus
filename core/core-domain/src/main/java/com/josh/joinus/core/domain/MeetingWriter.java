package com.josh.joinus.core.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingWriter {
    private MeetingRepository meetingRepository;
    public Meeting create(MeetingCreate meetingCreate) {
        return meetingRepository.create(meetingCreate);
    }
}
