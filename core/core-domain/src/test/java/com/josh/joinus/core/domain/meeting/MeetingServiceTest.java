package com.josh.joinus.core.domain.meeting;

import com.josh.joinus.ContextTest;
import com.josh.joinus.core.domain.*;
import com.josh.joinus.core.dto.request.UserCreateRequest;
import com.josh.joinus.core.exception.MultipleMeetingsException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MeetingServiceTest extends ContextTest {

    @Autowired
    MeetingService meetingService;

    @Autowired
    MeetingRepository meetingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TechRepository techRepository;

    @Autowired
    PositionRepository positionRepository;

    @DisplayName("meeting을 생성할 수 있다.")
    @Test
    void create() {
        //given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .nickname("test")
                .careerYear(1)
                .build();

        User savedUser = userRepository.register(userCreateRequest);

        Position backEnd = positionRepository.add("BACK_END");
        Position frontEnd = positionRepository.add("FRONT_END");
        Position designer = positionRepository.add("DESIGNER");

        Tech springBoot = techRepository.add("Spring Boot");
        Tech mySql = techRepository.add("MySql");
        Tech react = techRepository.add("React");

        MeetingCreate meetingCreate = MeetingCreate.builder()
                .leaderUserId(savedUser.getId())
                .meetingName("test meetingName")
                .content("test content")
                .meetingType(MeetingType.PROJECT)
                .techIdList(List.of(backEnd.getId(), frontEnd.getId(), designer.getId()))
                .processWay(ProcessWay.ONOFFLINE)
                .startDateTime(LocalDateTime.of(2024, 3, 4, 9, 0, 0))
                .headCount(5)
                .expiredDateTime(LocalDateTime.of(2024, 6, 4, 9, 0, 0))
                .positionList(List.of(springBoot.getId(), mySql.getId(), react.getId()))
                .build();

        //when
        Meeting meeting = meetingService.create(meetingCreate);

        //then
        assertThat(meeting.getMeetingName()).isEqualTo("test meetingName");
        assertThat(meeting.getContent()).isEqualTo("test content");
        assertThat(meeting.getMeetingType()).isEqualTo(MeetingType.PROJECT);
        assertThat(meeting.getProcessWay()).isEqualTo(ProcessWay.ONOFFLINE);
        assertThat(meeting.getStartDateTime()).isEqualTo(LocalDateTime.of(2024, 3, 4, 9, 0, 0));
        assertThat(meeting.getExpiredDateTime()).isEqualTo(LocalDateTime.of(2024, 6, 4, 9, 0, 0));
        assertThat(meeting.getHeadCount()).isEqualTo(5);
    }

    @DisplayName("같은유저가 Project나 Study를 하나당 2개 만들수없다.")
    @Test
    void doNotMadeduplicateMeeting() {
        //given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .nickname("test")
                .careerYear(1)
                .build();

        User savedUser = userRepository.register(userCreateRequest);

        Position backEnd = positionRepository.add("BACK_END");
        Position frontEnd = positionRepository.add("FRONT_END");
        Position designer = positionRepository.add("DESIGNER");

        Tech springBoot = techRepository.add("Spring Boot");
        Tech mySql = techRepository.add("MySql");
        Tech react = techRepository.add("React");

        MeetingCreate meetingCreate = MeetingCreate.builder()
                .leaderUserId(savedUser.getId())
                .meetingName("test meetingName")
                .content("test content")
                .meetingType(MeetingType.PROJECT)
                .techIdList(List.of(backEnd.getId(), frontEnd.getId(), designer.getId()))
                .processWay(ProcessWay.ONOFFLINE)
                .startDateTime(LocalDateTime.of(2024, 3, 4, 9, 0, 0))
                .headCount(5)
                .expiredDateTime(LocalDateTime.of(2024, 6, 4, 9, 0, 0))
                .positionList(List.of(springBoot.getId(), mySql.getId(), react.getId()))
                .build();

        MeetingCreate meetingCreate2 = MeetingCreate.builder()
                .leaderUserId(savedUser.getId())
                .meetingName("test meetingName")
                .content("test content")
                .meetingType(MeetingType.PROJECT)
                .techIdList(List.of(backEnd.getId(), frontEnd.getId(), designer.getId()))
                .processWay(ProcessWay.ONOFFLINE)
                .startDateTime(LocalDateTime.of(2024, 3, 4, 9, 0, 0))
                .headCount(5)
                .expiredDateTime(LocalDateTime.of(2024, 6, 4, 9, 0, 0))
                .positionList(List.of(springBoot.getId(), mySql.getId(), react.getId()))
                .build();

        meetingService.create(meetingCreate);

        //when
        //then
        assertThatThrownBy(() -> meetingService.create(meetingCreate2))
                .isInstanceOf(MultipleMeetingsException.class)
                .hasMessage("스터디나 사이드프로젝트 중 하나에 2개 이상의 모임을 만들수 없습니다.");


    }


}