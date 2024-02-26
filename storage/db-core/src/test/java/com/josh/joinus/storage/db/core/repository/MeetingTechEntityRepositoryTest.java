package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.*;
import com.josh.joinus.core.domain.meeting.MeetingCreate;
import com.josh.joinus.core.domain.meeting.MeetingStatus;
import com.josh.joinus.core.domain.meeting.MeetingType;
import com.josh.joinus.storage.db.core.CoreDbContextTest;
import com.josh.joinus.storage.db.core.entity.MeetingEntity;
import com.josh.joinus.storage.db.core.entity.MeetingTechEntity;
import com.josh.joinus.storage.db.core.entity.TechEntity;
import com.josh.joinus.storage.db.core.persistence.MeetingJpaRepository;
import com.josh.joinus.storage.db.core.persistence.MeetingTechJpaRepository;
import com.josh.joinus.storage.db.core.persistence.TechJpaRepository;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class MeetingTechEntityRepositoryTest extends CoreDbContextTest {
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
    TechJpaRepository techJpaRepository;

    @DisplayName("특정모임의 필요기술스택들을 등록할 수 있다.")
    @Test
    void create() {
        //given
        TechEntity springBoot = techJpaRepository.save(TechEntity.create("spring boot"));
        TechEntity mySql = techJpaRepository.save(TechEntity.create("MySql"));

        MeetingCreate testMeeting = MeetingCreate.builder()
                .meetingStatus(MeetingStatus.RECRUITING)
                .meetingName("test meeting")
                .meetingType(MeetingType.PROJECT)
                .expiredDateTime(LocalDateTime.of(2024, 2, 13, 12, 00, 00))
                .headCount(5)
                .processWay(ProcessWay.ONOFFLINE)
                .build();

        MeetingEntity meetingEntity = MeetingEntity.create(testMeeting);

        MeetingEntity savedMeeting = meetingJpaRepository.save(meetingEntity);

        //when
        meetingTechEntityRepository.create(savedMeeting.getId(), List.of(springBoot.getId(), mySql.getId()));
        List<MeetingTechEntity> result = meetingTechJpaRepository.findByMeetingEntity(savedMeeting);

        //then
        assertThat(result).hasSize(2)
                .extracting("id", "meetingEntity", "techEntity")
                .containsExactlyInAnyOrder(
                        Tuple.tuple(1L, savedMeeting, springBoot),
                        Tuple.tuple(2L, savedMeeting, mySql)
                );

    }


}