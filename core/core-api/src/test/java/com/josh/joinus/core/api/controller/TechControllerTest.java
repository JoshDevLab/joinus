package com.josh.joinus.core.api.controller;

import com.josh.joinus.core.api.TechRequest;
import com.josh.joinus.core.domain.Tech;
import com.josh.joinus.core.domain.TechService;
import com.josh.joinus.test.api.RestDocsTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.josh.joinus.test.api.RestDocsUtils.requestPreprocessor;
import static com.josh.joinus.test.api.RestDocsUtils.responsePreprocessor;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

class TechControllerTest extends RestDocsTest {

    private TechService techService;

    private TechController techController;

    @BeforeEach
    public void setUp() {
        techService = mock(TechService.class);
        techController = new TechController(techService);
        mockMvc = mockController(techController);
    }

    @DisplayName("추가하고자 하는 기술스택의 이름을 입력한다.")
    @Test
    void add() {

        when(techService.add(any())).thenReturn(
                Tech.builder()
                    .id(1L)
                    .name("Spring Boot")
                    .build());

        given().contentType(ContentType.JSON)
                .body(new TechRequest("Spring Boot"))
                .post("/api/v1/tech/add")
                .then()
                .status(HttpStatus.OK)
                .apply(
                       document(
                             "add",
                             requestPreprocessor(),
                             responsePreprocessor(),
                             requestFields(
                                     fieldWithPath("name")
                                             .type(JsonFieldType.STRING)
                                             .description("Tech Name")),
                               responseFields(
                                       fieldWithPath("name")
                                               .type(JsonFieldType.STRING)
                                               .description("tech name"),
                                       fieldWithPath("id")
                                               .type(JsonFieldType.NUMBER)
                                               .description("tech id"))

                       )
                );

    }

}