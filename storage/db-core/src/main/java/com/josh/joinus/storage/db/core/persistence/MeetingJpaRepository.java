package com.josh.joinus.storage.db.core.persistence;

import com.josh.joinus.storage.db.core.entity.MeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingJpaRepository extends JpaRepository<MeetingEntity, Long> {
}
