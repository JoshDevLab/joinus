package com.josh.joinus.core.domain;

import com.josh.joinus.core.dto.request.UserCreateRequest;

public interface UserRepository {
    User register(UserCreateRequest userCreateRequest);

    User findById(Long joinUserId);
}
