package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.User;
import com.josh.joinus.core.domain.UserRepository;
import com.josh.joinus.core.dto.request.UserCreateRequest;
import com.josh.joinus.storage.db.core.entity.UserEntity;
import com.josh.joinus.storage.db.core.persistence.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserEntityRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User register(UserCreateRequest userCreateRequest) {
        return userJpaRepository.save(UserEntity.create(userCreateRequest)).toDomain();
    }

    @Override
    public User findById(Long joinUserId) {
        return userJpaRepository.findById(joinUserId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."))
                .toDomain();
    }
}
