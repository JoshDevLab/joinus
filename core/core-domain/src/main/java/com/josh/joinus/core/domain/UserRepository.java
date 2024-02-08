package com.josh.joinus.core.domain;

import com.josh.joinus.core.dto.request.UserCreateRequest;
import com.josh.joinus.core.dto.response.UserResponse;

public interface UserRepository {
    User register(UserCreateRequest userCreateRequest);
}
