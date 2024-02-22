package com.josh.joinus.storage.db.core.persistence;

import com.josh.joinus.storage.db.core.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionJpaRepository extends JpaRepository<PositionEntity, Long> {
    List<PositionEntity> findByIdIn(List<Long> positionList);
}
