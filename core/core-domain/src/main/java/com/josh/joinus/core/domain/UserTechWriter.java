package com.josh.joinus.core.domain;

import com.josh.joinus.core.dto.request.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserTechWriter {
    private final UserTechRepository userTechRepository;

    public void register(Long userId, UserCreateRequest userCreateRequest) {
        userTechRepository.register(userId, userCreateRequest);
    }
}
