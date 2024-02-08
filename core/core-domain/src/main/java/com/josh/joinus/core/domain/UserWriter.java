package com.josh.joinus.core.domain;

import com.josh.joinus.core.dto.request.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserWriter {
    private final UserRepository userRepository;

    public User register(UserCreateRequest userCreateRequest) {
        return userRepository.register(userCreateRequest);
    }
}
