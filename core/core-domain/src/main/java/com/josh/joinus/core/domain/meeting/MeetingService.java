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

    private final MeetingCommentReader meetingCommentReader;

    @Transactional
    public Meeting create(MeetingCreate meetingCreate) {
        // validate
        meetingReader.findByUserIdDuplicate(meetingCreate.getLeaderUserId(), meetingCreate.getMeetingType());

        // create
        Meeting meeting = meetingWriter.create(meetingCreate);
        meetingTechWriter.create(meeting.getId(), meetingCreate.getTechIdList());
        meetingPositionWriter.create(meeting.getId(), meetingCreate.getPositionList());

        return meeting;
    }

    public List<MeetingResponse> searchByCondition(MeetingSearchCondition condition) {
        return meetingReader.searchByCondition(condition);
    }

    @Transactional
    public MeetingResponse meetingDetail(Long id, Long userId) {
        Meeting meeting = meetingReader.findByIdMeetingDetail(id);
        meeting.incrementViewCount(userId);
        return null;
    }
}
