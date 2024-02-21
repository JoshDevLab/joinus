package com.josh.joinus.storage.db.core.persistence;

import com.josh.joinus.storage.db.core.entity.MeetingPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingPositionJpaRepository extends JpaRepository<MeetingPositionEntity, Long> {
}
