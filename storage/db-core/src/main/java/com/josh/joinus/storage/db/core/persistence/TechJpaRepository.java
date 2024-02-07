package com.josh.joinus.storage.db.core.persistence;

import com.josh.joinus.storage.db.core.entity.TechEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechJpaRepository extends JpaRepository<TechEntity, Long> {
}
