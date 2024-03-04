package com.josh.joinus.core.domain.meeting;

import com.josh.joinus.core.domain.Position;
import com.josh.joinus.core.domain.Tech;
import com.josh.joinus.core.dto.request.MeetingSearchCondition;
import com.josh.joinus.core.dto.response.MeetingPositionDto;
import com.josh.joinus.core.dto.response.MeetingResponse;
import com.josh.joinus.core.dto.response.MeetingTechDto;
import com.josh.joinus.core.exception.MultipleMeetingsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Component
@RequiredArgsConstructor
public class MeetingReader {
    private final MeetingRepository meetingRepository;
    private final MeetingTechRepository meetingTechRepository;
    private final MeetingPositionRepository meetingPositionRepository;
    private final MeetingCommentRepository meetingCommentRepository;

    public List<MeetingResponse> searchByCondition(MeetingSearchCondition condition) {
        List<Meeting> meetingList = meetingRepository.searchByCondition(condition);
        List<Long> meetingIds = meetingList.stream().map(Meeting::getId).toList();

        List<MeetingPositionDto> meetingPositionDtoList = meetingRepository.findByMeetingPositionByMeetingIds(meetingIds);
        List<MeetingTechDto> meetingTechDtoList = meetingRepository.findByMeetingTechByMeetingIds(meetingIds);

        Map<Long, List<MeetingPositionDto>> meetingPositionMap =
                meetingPositionDtoList.stream().collect(groupingBy(MeetingPositionDto::getMeetingId));

        Map<Long, List<MeetingTechDto>> meetingTechMap =
                meetingTechDtoList.stream().collect(groupingBy(MeetingTechDto::getMeetingId));

        return meetingList.stream().map(
                p -> MeetingResponse.builder()
                        .meetingId(p.getId())
                        .meetingName(p.getMeetingName())
                        .expiredDate(p.getExpiredDateTime())
                        .positionList(meetingPositionMap.get(p.getId())
                            .stream().map(MeetingPositionDto::getPositionName).toList()
                        )
                        .techList(meetingTechMap.get(p.getId())
                            .stream().map(tech -> MeetingTechDto.builder()
                                        .techName(tech.getTechName())
                                        .techImg(tech.getTechImg()).build()).toList()
                        )
                        .build()
                ).toList();
    }

    public void findByUserIdDuplicate(Long leaderUserId, MeetingType meetingType) {
        if (meetingRepository.duplicateLeaderUser(leaderUserId, meetingType)) {
            throw new MultipleMeetingsException("스터디나 사이드프로젝트 중 하나에 2개 이상의 모임을 만들수 없습니다.");
        }
    }

    public Meeting findById(Long id) {
        return meetingRepository.findById(id);
    }

    public MeetingDetailResponse findByIdMeetingDetail(Meeting meeting) {
        List<Tech> techList = meetingTechRepository.findByMeetingId(meeting.getId());
        List<Position> positionList = meetingPositionRepository.findByMeetingId(meeting.getId());
        List<MeetingComment> meetingCommentList = meetingCommentRepository.findByMeetingId(meeting.getId());
        return MeetingDetailResponse.create(meeting, techList, positionList, meetingCommentList);
    }

    public Meeting findByIdLock(Long meetingId) {
        return meetingRepository.findByIdLock(meetingId);
    }
}
