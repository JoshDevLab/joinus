package com.josh.joinus.storage.db.core.persistence;

import com.josh.joinus.core.domain.Tech;
import com.josh.joinus.storage.db.core.entity.TechEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.desktop.QuitEvent;
import java.util.Collection;
import java.util.List;

public interface TechJpaRepository extends JpaRepository<TechEntity, Long> {
    List<TechEntity> findByIdIn(List<Long> id);
}
