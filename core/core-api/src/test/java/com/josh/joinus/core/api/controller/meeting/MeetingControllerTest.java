package com.josh.joinus.core.api.controller.meeting;

import com.josh.joinus.core.api.TechRequest;
import com.josh.joinus.core.api.controller.TechController;
import com.josh.joinus.core.api.dto.meeting.MeetingCreateRequest;
import com.josh.joinus.core.domain.Tech;
import com.josh.joinus.core.domain.TechService;
import com.josh.joinus.core.domain.meeting.MeetingCreate;
import com.josh.joinus.core.domain.meeting.MeetingService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;


class MeetingControllerTest extends RestDocsTest {

    private MeetingService meetingService = mock(MeetingService.class);

    @Override
    protected Object initController() {
        return new MeetingController(meetingService);
    }


    @DisplayName("미팅을 생성한다.")
    @Test
    void add() {

    }


}