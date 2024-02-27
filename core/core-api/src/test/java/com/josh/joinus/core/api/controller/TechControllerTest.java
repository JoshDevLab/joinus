package com.josh.joinus.core.api.controller;

import com.josh.joinus.core.api.controller.meeting.MeetingController;
import com.josh.joinus.core.api.dto.meeting.MeetingCreateRequest;
import com.josh.joinus.core.domain.ProcessWay;
import com.josh.joinus.core.domain.meeting.Meeting;
import com.josh.joinus.core.domain.meeting.MeetingCreate;
import com.josh.joinus.core.domain.meeting.MeetingService;
import com.josh.joinus.core.domain.meeting.MeetingType;
import com.josh.joinus.test.api.RestDocsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

class TechControllerTest extends RestDocsTest {

    @Override
    protected Object initController() {
        return null;
    }
}