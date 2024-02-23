package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.*;
import com.josh.joinus.core.dto.request.MeetingSearchCondition;
import com.josh.joinus.storage.db.core.CoreDbContextTest;
import com.josh.joinus.storage.db.core.entity.*;
import com.josh.joinus.storage.db.core.persistence.MeetingJpaRepository;
import com.josh.joinus.storage.db.core.persistence.PositionJpaRepository;
import com.josh.joinus.storage.db.core.persistence.TechJpaRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
class MeetingEntityRepositoryTest extends CoreDbContextTest {

    @Autowired
    MeetingEntityRepository meetingEntityRepository;

    @Autowired
    MeetingTechEntityRepository meetingTechEntityRepository;

    @Autowired
    MeetingPositionEntityRepository meetingPositionEntityRepository;

    @Autowired
    MeetingJpaRepository meetingJpaRepository;

    @Autowired
    PositionJpaRepository positionJpaRepository;

    @Autowired
    TechJpaRepository techJpaRepository;

    @DisplayName("모임을 생성할 수 있다.")
    @Test
    void create() {
        //given
        MeetingCreate testMeeting = MeetingCreate.builder()
                .leaderUserId(1L)
                .meetingStatus(MeetingStatus.RECRUITING)
                .meetingName("test meeting")
                .meetingType(MeetingType.PROJECT)
                .expiredDateTime(
                        LocalDateTime.of(2024, 2, 13, 12, 00, 00)
                )
                .headCount(5)
                .processWay(ProcessWay.ONOFFLINE)
                .build();


        //when
        Meeting result = meetingEntityRepository.create(testMeeting);

        //then
        assertThat(result.getLeaderUserId()).isEqualTo(1L);
        assertThat(result.getMeetingStatus()).isEqualTo(MeetingStatus.RECRUITING);
        assertThat(result.getMeetingName()).isEqualTo("test meeting");
        assertThat(result.getMeetingType()).isEqualTo(MeetingType.PROJECT);
        assertThat(result.getExpiredDateTime())
                .isEqualTo(
                        LocalDateTime.of(2024, 2, 13, 12, 00, 00)
                );
        assertThat(result.getHeadCount()).isEqualTo(5);
        assertThat(result.getProcessWay()).isEqualTo(ProcessWay.ONOFFLINE);

    }

    @Test
    void searchByCondition() {
        //given
        TechEntity springBoot = techJpaRepository.save(TechEntity.builder().name("Spring Boot").build());
        TechEntity mySql = techJpaRepository.save(TechEntity.builder().name("MySql").build());
        TechEntity react = techJpaRepository.save(TechEntity.builder().name("React").build());

        PositionEntity backEnd = positionJpaRepository.save(new PositionEntity("BACK_END"));
        PositionEntity frontEnd = positionJpaRepository.save(new PositionEntity("FRONT_END"));
        PositionEntity designer = positionJpaRepository.save(new PositionEntity("DESIGNER"));

        MeetingSearchCondition recruitingSearch = MeetingSearchCondition.builder()
                .meetingStatus(MeetingStatus.RECRUITING)
                .build();

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
        List<Meeting> meetingList = meetingEntityRepository.searchByCondition(recruitingSearch);

        //then
        Assertions.assertThat(meetingList).hasSize(2)
                .extracting("meetingStatus", "meetingName", "meetingType", "processWay"
                            , "expiredDateTime", "headCount")
                .containsExactlyInAnyOrder(
                        Tuple.tuple(MeetingStatus.RECRUITING, "test meeting", MeetingType.PROJECT,
                                ProcessWay.ONOFFLINE,
                                LocalDateTime.of(2024, 2, 13, 12, 00, 00),
                                5
                                ),
                        Tuple.tuple(MeetingStatus.RECRUITING, "test meeting2", MeetingType.STUDY,
                                ProcessWay.ONOFFLINE,
                                LocalDateTime.of(2024, 2, 15, 12, 00, 00),
                                7
                                )
                );

    }
}