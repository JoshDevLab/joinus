package com.josh.joinus.core.api.controller.meeting;

import com.josh.joinus.core.api.dto.meeting.MeetingCreateRequest;
import com.josh.joinus.core.api.reuqest.MeetingSearchRequest;
import com.josh.joinus.core.domain.meeting.Meeting;
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
    public ResponseEntity<Meeting> create(@Valid @RequestBody MeetingCreateRequest meetingCreateRequest) {
        return ResponseEntity.ok(meetingService.create(meetingCreateRequest.toServiceDto()));
    }

    @GetMapping
    public ResponseEntity<List<MeetingResponse>> searchByCondition(
            @ModelAttribute MeetingSearchRequest meetingSearchRequest)
    {
        return ResponseEntity.ok(meetingService.searchByCondition(meetingSearchRequest.toServiceDto()));
    }
}
