package com.josh.joinus.core.domain;

import com.josh.joinus.core.dto.request.UserCreateRequest;

public interface UserTechRepository {
    void register(Long userId, UserCreateRequest userCreateRequest);
}
