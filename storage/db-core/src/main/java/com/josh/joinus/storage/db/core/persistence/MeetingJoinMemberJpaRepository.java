package com.josh.joinus.storage.db.core.persistence;

import com.josh.joinus.core.domain.meeting.MeetingType;
import com.josh.joinus.storage.db.core.entity.MeetingJoinMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingJoinMemberJpaRepository extends JpaRepository<MeetingJoinMemberEntity, Long> {
    List<MeetingJoinMemberEntity> findByMeetingId(Long meetingId);
}
