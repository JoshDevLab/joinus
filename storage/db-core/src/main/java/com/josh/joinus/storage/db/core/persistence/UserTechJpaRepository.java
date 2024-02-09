package com.josh.joinus.storage.db.core.persistence;

import com.josh.joinus.storage.db.core.entity.UserTechEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTechJpaRepository extends JpaRepository<UserTechEntity, Long> {
}
