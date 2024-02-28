package com.josh.joinus.core.api.controller.meeting;

import com.josh.joinus.core.api.dto.meeting.MeetingCreateRequest;
import com.josh.joinus.core.domain.ProcessWay;
import com.josh.joinus.core.domain.meeting.*;
import com.josh.joinus.test.api.RestDocsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.post;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class MeetingControllerTest extends RestDocsTest {

    private final MeetingService meetingService = mock(MeetingService.class);

    @Override
    protected Object initController() {
        return new MeetingController(meetingService);
    }

    @DisplayName("미팅을 생성하는 API")
    @Test
    void create() throws Exception {
        //given
        MeetingCreateRequest request = MeetingCreateRequest.builder()
                .leaderUserId(1L)
                .meetingName("test")
                .content("test")
                .meetingType(MeetingType.PROJECT)
                .techIdList(List.of(1L, 2L))
                .processWay(ProcessWay.ONOFFLINE)
                .startDateTime(LocalDateTime.of(2024, 3, 4, 9, 0, 0))
                .headCount(5)
                .positionList(List.of(1L, 2L))
                .expiredDateTime(LocalDateTime.of(2024, 6, 4, 9, 0, 0))
                .build();

        given(meetingService.create(any(MeetingCreate.class)))
                .willReturn(Meeting.builder()
                        .id(1L)
                        .leaderUserId(1L)
                        .meetingName("test meetingName")
                        .content("test content")
                        .meetingType(MeetingType.PROJECT)
                        .processWay(ProcessWay.ONOFFLINE)
                        .meetingStatus(MeetingStatus.RECRUITING)
                        .startDateTime(LocalDateTime.of(2024, 3, 4, 9, 0, 0))
                        .headCount(5)
                        .expiredDateTime(LocalDateTime.of(2024, 6, 4, 9, 0, 0))
                        .build());

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/meeting")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("meeting-create",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("leaderUserId").type(JsonFieldType.NUMBER)
                                        .description("방장 아이디"),
                                fieldWithPath("meetingName").type(JsonFieldType.STRING)
                                    .description("모임 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING)
                                    .description("모임 내용"),
                                fieldWithPath("meetingType").type(JsonFieldType.STRING)
                                        .description("모임 타입"),
                                fieldWithPath("techIdList").type(JsonFieldType.ARRAY)
                                        .description("필요기술스택 ID"),
                                fieldWithPath("processWay").type(JsonFieldType.STRING)
                                        .description("모임진행방식"),
                                fieldWithPath("startDateTime").type(JsonFieldType.ARRAY)
                                        .description("모임 시작날짜"),
                                fieldWithPath("headCount").type(JsonFieldType.NUMBER)
                                        .description("모임 인원"),
                                fieldWithPath("positionList").type(JsonFieldType.ARRAY)
                                        .description("필요 포지션"),
                                fieldWithPath("expiredDateTime").type(JsonFieldType.ARRAY)
                                        .description("마감일")
                        ),responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER)
                                        .description("모임 ID"),
                                fieldWithPath("leaderUserId").type(JsonFieldType.NUMBER)
                                        .description("방장 아이디"),
                                fieldWithPath("meetingName").type(JsonFieldType.STRING)
                                        .description("모임 이름"),
                                fieldWithPath("content").type(JsonFieldType.STRING)
                                        .description("모임 내용"),
                                fieldWithPath("meetingType").type(JsonFieldType.STRING)
                                        .description("모임 타입"),
                                fieldWithPath("processWay").type(JsonFieldType.STRING)
                                        .description("모집진행방식"),
                                fieldWithPath("meetingStatus").type(JsonFieldType.STRING)
                                        .description("모임 내용"),
                                fieldWithPath("content").type(JsonFieldType.STRING)
                                        .description("모임중"),
                                fieldWithPath("startDateTime").type(JsonFieldType.ARRAY)
                                        .description("모임 시작날짜"),
                                fieldWithPath("headCount").type(JsonFieldType.NUMBER)
                                        .description("인원수"),
                                fieldWithPath("expiredDateTime").type(JsonFieldType.ARRAY)
                                        .description("마감일")

                        )
                    ));

    }


}