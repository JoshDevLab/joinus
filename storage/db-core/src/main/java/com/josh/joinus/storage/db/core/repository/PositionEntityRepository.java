package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.Position;
import com.josh.joinus.core.domain.PositionRepository;
import com.josh.joinus.storage.db.core.entity.PositionEntity;
import com.josh.joinus.storage.db.core.persistence.PositionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PositionEntityRepository implements PositionRepository {

    private final PositionJpaRepository positionJpaRepository;
    @Override
    public Position add(String positionName) {
        return positionJpaRepository.save(PositionEntity.create(positionName)).toDomain();
    }
}
