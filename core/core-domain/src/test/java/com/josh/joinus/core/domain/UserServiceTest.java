package com.josh.joinus.core.domain;

import com.josh.joinus.core.ContextTest;
import com.josh.joinus.core.domain.Position;
import com.josh.joinus.core.domain.TechRepository;
import com.josh.joinus.core.domain.UserService;
import com.josh.joinus.core.dto.request.UserCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class UserServiceTest extends ContextTest {

    @Autowired
    private UserService userService;

    @Autowired
    private TechRepository techRepository;

    @Test
    void register() {

        userService.register(new UserCreateRequest("a", Position.BACK_END, 1, List.of(1L, 2L)));
        techRepository.add("abc");

        UserCreateRequest.builder()
                .nickname("josh")
                .position(Position.BACK_END)
                .techIdList(List.of(1L, 2L))
                .careerYear(1)
                .build();


    }
}