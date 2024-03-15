package com.josh.joinus.storage.db.core.persistence;

import com.josh.joinus.core.domain.User;
import com.josh.joinus.storage.db.core.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserId(String username);
}
