package com.josh.joinus.core.api.controller.meeting;

import com.josh.joinus.core.api.dto.meeting.MeetingCreateRequest;
import com.josh.joinus.core.domain.meeting.Meeting;
import com.josh.joinus.core.domain.meeting.MeetingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("meeting")
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;

    @PostMapping
    public ResponseEntity<Meeting> create(@Valid @RequestBody MeetingCreateRequest meetingCreateRequest) {
        return ResponseEntity.ok(meetingService.create(meetingCreateRequest.toServiceDto()));
    }
}
