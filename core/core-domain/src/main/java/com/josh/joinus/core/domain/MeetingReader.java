package com.josh.joinus.core.domain;

import com.josh.joinus.core.dto.request.MeetingSearchCondition;
import com.josh.joinus.core.dto.response.MeetingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MeetingReader {
    private final MeetingRepository meetingRepository;

    public List<MeetingResponse> search(MeetingSearchCondition condition) {
        return null;
    }
}
