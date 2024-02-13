package com.josh.joinus.core.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingWriter meetingWriter;

    public Meeting create(MeetingCreate meetingCreate) {
        return meetingWriter.create(meetingCreate);
    }
}
