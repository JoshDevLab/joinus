package com.josh.joinus.core.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MeetingTechWriter {
    private final MeetingTechRepository meetingTechRepository;

    public void create(Long meetingId, List<Long> techIdList) {
        meetingTechRepository.create(meetingId, techIdList);
    }
}
