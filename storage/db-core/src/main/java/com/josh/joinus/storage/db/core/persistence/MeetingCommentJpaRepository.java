package com.josh.joinus.storage.db.core.persistence;

import com.josh.joinus.storage.db.core.entity.MeetingCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingCommentJpaRepository extends JpaRepository<MeetingCommentEntity, Long> {
}
