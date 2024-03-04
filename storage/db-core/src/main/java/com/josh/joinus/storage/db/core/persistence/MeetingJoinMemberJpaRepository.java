package com.josh.joinus.storage.db.core.persistence;

import com.josh.joinus.core.domain.meeting.MeetingType;
import com.josh.joinus.storage.db.core.entity.MeetingJoinMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingJoinMemberJpaRepository extends JpaRepository<MeetingJoinMemberEntity, Long> {
}
