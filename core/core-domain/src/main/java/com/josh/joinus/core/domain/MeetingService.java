package com.josh.joinus.core.domain;

import com.josh.joinus.core.dto.request.MeetingSearchCondition;
import com.josh.joinus.core.dto.response.MeetingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingWriter meetingWriter;
    private final MeetingReader meetingReader;

    private final MeetingTechWriter meetingTechWriter;
    private final MeetingPositionWriter meetingPositionWriter;

    // 추후 트랜잭션 의존성 주입 후 어노테이션 추가
    @Transactional
    public Meeting create(MeetingCreate meetingCreate) {
        Meeting meeting = meetingWriter.create(meetingCreate);
        meetingTechWriter.create(meeting.getId(), meetingCreate.getTechIdList());
        meetingPositionWriter.create(meeting.getId(), meetingCreate.getPositionList());
        return meeting;
    }

    public List<MeetingResponse> searchByCondition(MeetingSearchCondition condition) {
        return meetingReader.searchByCondition(condition);
    }
}
