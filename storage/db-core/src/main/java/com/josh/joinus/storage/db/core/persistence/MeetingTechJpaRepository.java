package com.josh.joinus.storage.db.core.persistence;

import com.josh.joinus.storage.db.core.entity.MeetingEntity;
import com.josh.joinus.storage.db.core.entity.MeetingTechEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingTechJpaRepository extends JpaRepository<MeetingTechEntity, Long> {
    List<MeetingTechEntity> findByMeetingEntity(MeetingEntity meetingEntity);
}
