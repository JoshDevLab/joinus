package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.*;
import com.josh.joinus.core.dto.request.MeetingSearchCondition;
import com.josh.joinus.core.dto.response.MeetingTechDto;
import com.josh.joinus.storage.db.core.CoreDbContextTest;
import com.josh.joinus.storage.db.core.entity.MeetingEntity;
import com.josh.joinus.storage.db.core.entity.MeetingPositionEntity;
import com.josh.joinus.storage.db.core.entity.MeetingTechEntity;
import com.josh.joinus.storage.db.core.entity.PositionEntity;
import com.josh.joinus.storage.db.core.persistence.MeetingJpaRepository;
import com.josh.joinus.storage.db.core.persistence.MeetingTechJpaRepository;
import com.josh.joinus.storage.db.core.persistence.PositionJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
class MeetingEntityRepositoryTest extends CoreDbContextTest {
    @Autowired
    MeetingTechEntityRepository meetingTechEntityRepository;

    @Autowired
    MeetingTechJpaRepository meetingTechJpaRepository;

    @Autowired
    TechEntityRepository techEntityRepository;

    @Autowired
    UserEntityRepository userEntityRepository;

    @Autowired
    MeetingJpaRepository meetingJpaRepository;

    @Autowired
    MeetingEntityRepository meetingEntityRepository;

    @Autowired
    PositionJpaRepository positionJpaRepository;

    @Autowired
    MeetingPositionEntityRepository meetingPositionEntityRepository;

    @Test
    void search() {
        //given
        MeetingSearchCondition condition = new MeetingSearchCondition();

        Tech springBoot = techEntityRepository.add("Spring Boot");
        Tech mySql = techEntityRepository.add("MySql");
        Tech react = techEntityRepository.add("React");

        PositionEntity backEnd = positionJpaRepository.save(new PositionEntity("BACK_END"));
        PositionEntity frontEnd = positionJpaRepository.save(new PositionEntity("FRONT_END"));
        PositionEntity designer = positionJpaRepository.save(new PositionEntity("DESIGNER"));

        MeetingCreate testMeeting = MeetingCreate.builder()
                .meetingStatus(MeetingStatus.RECRUITING)
                .meetingName("test meeting")
                .meetingType(MeetingType.PROJECT)
                .expiredDateTime(
                        LocalDateTime.of(2024, 2, 13, 12, 00, 00)
                )
                .headCount(5)
                .processWay(ProcessWay.ONOFFLINE)
                .build();

        MeetingEntity meetingEntity = MeetingEntity.create(testMeeting);
        MeetingEntity savedMeeting = meetingJpaRepository.save(meetingEntity);
        meetingTechEntityRepository.create(savedMeeting.getId(), List.of(springBoot.getId(), mySql.getId()));
        meetingPositionEntityRepository.create(savedMeeting.getId(), List.of(backEnd.getId(),
                frontEnd.getId(),
                designer.getId()));

        MeetingCreate testMeeting2 = MeetingCreate.builder()
                .meetingStatus(MeetingStatus.RECRUITING)
                .meetingName("test meeting2")
                .meetingType(MeetingType.STUDY)
                .expiredDateTime(
                        LocalDateTime.of(2024, 2, 15, 12, 00, 00)
                )
                .headCount(7)
                .processWay(ProcessWay.ONOFFLINE)
                .build();

        MeetingEntity meetingEntity2 = MeetingEntity.create(testMeeting2);
        MeetingEntity savedMeeting2 = meetingJpaRepository.save(meetingEntity2);
        meetingTechEntityRepository.create(savedMeeting2.getId(), List.of(springBoot.getId(),
                                                                            mySql.getId(),
                                                                            react.getId()));
        meetingPositionEntityRepository.create(savedMeeting2.getId(), List.of(backEnd.getId(),
                                                                            frontEnd.getId()));

        //when
        List<MeetingTechDto> byMeetingTechByMeetingIds =
                meetingEntityRepository.findByMeetingTechByMeetingIds(List.of(meetingEntity.getId(),
                                                                                meetingEntity2.getId()));

        System.out.println("byMeetingTechByMeetingIds = " + byMeetingTechByMeetingIds);

        Map<Long, List<MeetingTechDto>> collect =
                byMeetingTechByMeetingIds.stream().collect(Collectors.groupingBy(MeetingTechDto::getMeetingId));
        System.out.println("collect = " + collect);
        //then
//        Assertions.assertThat(meetingList).hasSize(2)
//                .extracting("")
    }
}