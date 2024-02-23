package com.josh.joinus.core.domain;

import com.josh.joinus.core.dto.request.MeetingSearchCondition;
import com.josh.joinus.core.dto.response.MeetingPositionDto;
import com.josh.joinus.core.dto.response.MeetingResponse;
import com.josh.joinus.core.dto.response.MeetingTechDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Component
@RequiredArgsConstructor
public class MeetingReader {
    private final MeetingRepository meetingRepository;

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
                            .stream().map(MeetingTechDto::getTech).toList()
                        )
                        .build()
                ).toList();
    }
}
