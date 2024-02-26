package com.josh.joinus.core.domain.meeting;

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

    @Transactional
    public Long create(MeetingCreate meetingCreate) {
        // validate
        meetingReader.findByUserIdDuplicate(meetingCreate.getLeaderUserId(), meetingCreate.getMeetingType());

        // create
        Long meetingId = meetingWriter.create(meetingCreate);
        meetingTechWriter.create(meetingId, meetingCreate.getTechIdList());
        meetingPositionWriter.create(meetingId, meetingCreate.getPositionList());

        return meetingId;
    }

    public List<MeetingResponse> searchByCondition(MeetingSearchCondition condition) {
        return meetingReader.searchByCondition(condition);
    }
}
