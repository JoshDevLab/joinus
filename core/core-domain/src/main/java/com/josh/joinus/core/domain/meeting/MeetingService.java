package com.josh.joinus.core.domain.meeting;

import com.josh.joinus.core.domain.User;
import com.josh.joinus.core.domain.UserRepository;
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

    private final MeetingJoinMemberValidator meetingJoinMemberValidator;
    private final MeetingJoinMemberReader meetingJoinMemberReader;
    private final MeetingJoinMemberWriter meetingJoinMemberWriter;

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
    public MeetingDetailResponse meetingDetail(Long id, Long userId) {
        Meeting meeting = meetingReader.findById(id);

        if (meeting.incrementViewCount(userId)) {
            meetingWriter.updateViewCount(meeting.getId(), meeting.getViewCount());
        }

        return meetingReader.findByIdMeetingDetail(meeting);
    }

    @Transactional
    public MeetingJoinMember joinRequest(Long meetingId, Long joinUserId) {
        //모임의 인원
        Meeting meeting = meetingReader.findById(meetingId);

        // 모임 validation
        meeting.joinValidate();

        //참여하려는 유저의 중복모임 방지
        meetingJoinMemberValidator.duplicateValidation(meeting.getMeetingType(), joinUserId);

        // 모임유저테이블에 insert
        return meetingJoinMemberWriter.registerRequest(meetingId, joinUserId);
    }

    @Transactional
    public Long joinAccept(Long meetingJoinMemberId) {
        //모임의 인원
        MeetingJoinMember meetingJoinMember = meetingJoinMemberReader.findById(meetingJoinMemberId);
        Meeting meeting = meetingReader.findById(meetingJoinMember.getMeetingId());

        // 모임 validation
        meeting.joinValidate();

        // 모임인원 1명 감소
        meeting.reduceHeadCount();
        meetingWriter.updateHeadCount(meeting.getId(), meeting.getHeadCount());

        // 모임유저테이블 update
        return (long) meetingJoinMemberWriter.updateAccept(meetingJoinMemberId);
    }
}
