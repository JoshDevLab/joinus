package com.josh.joinus.core.api.controller.meeting;

import com.josh.joinus.core.api.dto.meeting.MeetingCreateRequest;
import com.josh.joinus.core.api.reuqest.MeetingSearchRequest;
import com.josh.joinus.core.domain.ProcessWay;
import com.josh.joinus.core.domain.meeting.*;
import com.josh.joinus.core.dto.request.MeetingSearchCondition;
import com.josh.joinus.core.dto.response.MeetingResponse;
import com.josh.joinus.core.dto.response.MeetingTechDto;
import com.josh.joinus.test.api.RestDocsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



class MeetingControllerTest extends RestDocsTest {

    private final MeetingService meetingService = mock(MeetingService.class);

    @Override
    protected Object initController() {
        return new MeetingController(meetingService);
    }

    @DisplayName("모임을 생성하는 API")
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
                        .viewCount(0)
                        .commentCount(0)
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
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING)
                                        .description("ResultType"),
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER)
                                        .description("모임 ID"),
                                fieldWithPath("data.leaderUserId").type(JsonFieldType.NUMBER)
                                        .description("방장 아이디"),
                                fieldWithPath("data.meetingName").type(JsonFieldType.STRING)
                                        .description("모임 이름"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING)
                                        .description("모임 내용"),
                                fieldWithPath("data.meetingType").type(JsonFieldType.STRING)
                                        .description("모임 타입"),
                                fieldWithPath("data.processWay").type(JsonFieldType.STRING)
                                        .description("모집진행방식"),
                                fieldWithPath("data.meetingStatus").type(JsonFieldType.STRING)
                                        .description("모임중"),
                                fieldWithPath("data.startDateTime").type(JsonFieldType.ARRAY)
                                        .description("모임 시작날짜"),
                                fieldWithPath("data.headCount").type(JsonFieldType.NUMBER)
                                        .description("인원수"),
                                fieldWithPath("data.expiredDateTime").type(JsonFieldType.ARRAY)
                                        .description("마감일"),
                                fieldWithPath("error").type(JsonFieldType.ARRAY)
                                        .description("에러")
                                        .ignored()

                        )
                    ));

    }

    @DisplayName("모임을 검색하는 API")
    @Test
    void searchByCondition() throws Exception {
        //given
        MeetingSearchRequest searchRequest = MeetingSearchRequest.builder()
                .meetingType(MeetingType.PROJECT)
                .techIdList(List.of(1L, 2L))
                .positionId(1L)
                .processWay(ProcessWay.ONOFFLINE)
                .meetingStatus(MeetingStatus.RECRUITING)
                .build();

        given(meetingService.searchByCondition(any(MeetingSearchCondition.class))).willReturn(
                List.of(
                        MeetingResponse.builder()
                                .meetingId(1L)
                                .meetingName("test meeting name")
                                .techList(List.of(new MeetingTechDto(1L, "tech name", "tech image"),
                                        new MeetingTechDto(1L, "tech name2", "tech image2")))
                                .positionList(List.of("position name1", "position name2", "position name3"))
                                .expiredDate(LocalDateTime.now())
                                .userNickname("userNickname")
                                .views(1)
                                .reviewCount(2)
                                .build(),
                        MeetingResponse.builder()
                                .meetingId(2L)
                                .meetingName("test meeting name2")
                                .techList(List.of(new MeetingTechDto(1L, "tech name", "tech image"),
                                        new MeetingTechDto(1L, "tech name2", "tech image2")))
                                .positionList(List.of("position name1", "position name2", "position name3"))
                                .expiredDate(LocalDateTime.now())
                                .userNickname("userNickname2")
                                .views(1)
                                .reviewCount(2)
                                .build()
                )
        );


        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/meeting?meetingType=PROJECT&techIdList=1,2&positionId=1" +
                                "&processWay=ONOFFLINE&meetingStatus=RECRUITING")
                        .content(objectMapper.writeValueAsString(searchRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("meeting-search",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        queryParameters(
                                parameterWithName("meetingType").description("모임 타입"),
                                parameterWithName("techIdList").description("기술 ID 리스트"),
                                parameterWithName("positionId").description("포지션 ID"),
                                parameterWithName("processWay").description("진행 방식"),
                                parameterWithName("meetingStatus").description("모임 상태")
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING)
                                        .description("ResultType"),
                                fieldWithPath("data[].meetingId").type(JsonFieldType.NUMBER)
                                        .description("모임 ID"),
                                fieldWithPath("data[].meetingName").type(JsonFieldType.STRING)
                                        .description("모임 이름"),
                                fieldWithPath("data[].techList").type(JsonFieldType.ARRAY)
                                        .description("필요기술"),
                                fieldWithPath("data[].techList[].meetingId").type(JsonFieldType.NUMBER)
                                        .description("모임 ID"),
                                fieldWithPath("data[].techList[].techName").type(JsonFieldType.STRING)
                                        .description("특정 모임의 필요기술 이름"),
                                fieldWithPath("data[].techList[].techImg").type(JsonFieldType.STRING)
                                        .description("특정 모임의 필요기술 이미지"),
                                fieldWithPath("data[].positionList").type(JsonFieldType.ARRAY)
                                        .description("필요포지션"),
                                fieldWithPath("data[].expiredDate").type(JsonFieldType.ARRAY)
                                        .description("마감일"),
                                fieldWithPath("data[].userNickname").type(JsonFieldType.STRING)
                                        .description("유저 닉네임"),
                                fieldWithPath("data[].views").type(JsonFieldType.NUMBER)
                                        .description("조회수"),
                                fieldWithPath("data[].reviewCount").type(JsonFieldType.NUMBER)
                                        .description("댓글수"),
                                fieldWithPath("error").type(JsonFieldType.ARRAY)
                                        .description("에러")
                                        .ignored()

                        )
                ));

    }


}