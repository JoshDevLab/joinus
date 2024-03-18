package com.josh.joinus.core.domain;

import com.josh.joinus.core.dto.request.UserCreateRequest;

public interface UserRepository {
    User register(UserCreateRequest userCreateRequest);

    User findById(Long joinUserId);

    User findByUserId(String username);

    void save(User user);

    void updateUserName(Long id, String name);

    void updateProfileImg(Long id, String imageUrl);
}
