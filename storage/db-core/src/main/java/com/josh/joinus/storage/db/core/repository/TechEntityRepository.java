package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.Tech;
import com.josh.joinus.core.domain.TechRepository;
import com.josh.joinus.storage.db.core.entity.TechEntity;
import com.josh.joinus.storage.db.core.persistence.TechJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TechEntityRepository implements TechRepository {

    private final TechJpaRepository techJpaRepository;

    @Override
    public Tech add(String name) {
        TechEntity techEntity = TechEntity.create(name);
        return techJpaRepository.save(techEntity).toDomain();
    }

    @Override
    public List<Tech> findAll() {
        return techJpaRepository.findAll()
                .stream().map(TechEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Tech> findByTechIdIn(List<Long> techIdList) {
        return techJpaRepository.findByIdIn(techIdList)
                .stream().map(TechEntity::toDomain)
                .collect(Collectors.toList());
    }
}
