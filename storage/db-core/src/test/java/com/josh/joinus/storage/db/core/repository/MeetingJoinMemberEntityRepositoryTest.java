package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.User;
import com.josh.joinus.core.domain.UserRepository;
import com.josh.joinus.core.domain.meeting.*;
import com.josh.joinus.core.dto.request.UserCreateRequest;
import com.josh.joinus.storage.db.core.CoreDbContextTest;
import com.josh.joinus.storage.db.core.entity.MeetingJoinMemberEntity;
import com.josh.joinus.storage.db.core.persistence.MeetingJoinMemberJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
class MeetingJoinMemberEntityRepositoryTest extends CoreDbContextTest {

    @Autowired
    MeetingJoinMemberRepository meetingJoinMemberRepository;

    @Autowired
    MeetingRepository meetingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MeetingJoinMemberJpaRepository meetingJoinMemberJpaRepository;

    @DisplayName("이미 같은 타입의 모임에 참여중인 인원은 참여할수없다.")
    @Test
    void findByMeetingTypeAndJoinUserId() {
        //given
        MeetingCreate meetingCreate = MeetingCreate.builder()
                .meetingType(MeetingType.PROJECT)
                .build();
        Meeting meeting = meetingRepository.create(meetingCreate);

        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .nickname("닉네임1")
                .build();

        User user = userRepository.register(userCreateRequest);

        MeetingJoinMemberEntity meetingJoinMemberEntity = MeetingJoinMemberEntity.builder()
                                                                                .userId(user.getId())
                                                                                .meetingId(meeting.getId())
                                                                                .build();

        meetingJoinMemberJpaRepository.save(meetingJoinMemberEntity);

        //when
        boolean result =
                meetingJoinMemberRepository.findByMeetingTypeAndJoinUserId(meeting.getMeetingType(), user.getId());

        //then
        assertThat(result).isTrue();
    }
}