package com.josh.joinus.storage.db.core.persistence;

import com.josh.joinus.storage.db.core.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionJpaRepository extends JpaRepository<PositionEntity, Long> {
}
