package com.josh.joinus.storage.db.core.persistence;

import com.josh.joinus.storage.db.core.entity.MeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeetingJpaRepository extends JpaRepository<MeetingEntity, Long> {
}
