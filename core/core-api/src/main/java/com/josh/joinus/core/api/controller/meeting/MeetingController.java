package com.josh.joinus.core.api.controller.meeting;

import com.josh.joinus.core.api.controller.meeting.response.MeetingCreateResponse;
import com.josh.joinus.core.api.dto.meeting.MeetingCreateRequest;
import com.josh.joinus.core.api.reuqest.MeetingSearchRequest;
import com.josh.joinus.core.api.support.response.ApiResponse;
import com.josh.joinus.core.domain.meeting.Meeting;
import com.josh.joinus.core.domain.meeting.MeetingDetailResponse;
import com.josh.joinus.core.domain.meeting.MeetingService;
import com.josh.joinus.core.dto.response.MeetingResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("meeting")
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;

    @PostMapping
    public ApiResponse<MeetingCreateResponse> create(@Valid @RequestBody MeetingCreateRequest meetingCreateRequest) {
        return ApiResponse.success(MeetingCreateResponse.of(meetingService.create(meetingCreateRequest.toServiceDto())));
    }

    @GetMapping
    public ApiResponse<List<MeetingResponse>> searchByCondition(
            @ModelAttribute MeetingSearchRequest meetingSearchRequest)
    {
        return ApiResponse.success(meetingService.searchByCondition(meetingSearchRequest.toServiceDto()));
    }

    @GetMapping("/{meetingId}")
    public ApiResponse<MeetingDetailResponse> meetingDetail(@PathVariable String meetingId) {
        // 추후 security 도입 후 접속한 user 의 id 넣어줌
        Long accessUserId = 3L;
        return ApiResponse.success(meetingService.meetingDetail(Long.valueOf(meetingId), accessUserId));
    }

    @PostMapping("/join/{meetingId}")
    public ApiResponse<Long> join(@PathVariable String meetingId) {
        // 추후 security 도입 후 접속한 user 의 id 넣어줌
        Long joinUserId = 3L;
        return ApiResponse.success(meetingService.joinRequest(Long.valueOf(meetingId), joinUserId).getId());
    }

    @PostMapping("/join/accept/{meetingJoinMemberId}")
    public ApiResponse<Long> joinAccept(@PathVariable String meetingJoinMemberId) {
        // 추후 security 도입 후 접속한 user 의 id 넣어줌
        Long joinUserId = 3L;
        return ApiResponse.success(meetingService.joinAccept(Long.valueOf(meetingJoinMemberId)));
    }
}
