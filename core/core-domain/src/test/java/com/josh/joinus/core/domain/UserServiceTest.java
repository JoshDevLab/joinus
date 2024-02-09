package com.josh.joinus.core.domain;

import com.josh.joinus.core.dto.request.UserCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    TechRepository techRepository;

    @Test
    void register() {


        UserCreateRequest.builder()
                .nickname("josh")
                .position(Position.BACK_END)
                .techIdList(List.of(1L, 2L))
                .careerYear(1)
                .build();


    }
}