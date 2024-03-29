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

    @Override
    public User findByUserId(String username) {
        return userJpaRepository.findByUserId(username).toDomain();
    }

    @Override
    public void save(User user) {
        userJpaRepository.save(UserEntity.of(user));
    }

    @Override
    public void updateUserName(Long id, String name) {
        userJpaRepository.updateUserName(id, name);
    }

    @Override
    public void updateProfileImg(Long id, String imageUrl) {
        userJpaRepository.updateProfileImg(id, imageUrl);
    }
}
