package com.josh.joinus.core.domain.meeting;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MeetingStatus {
    RECRUITING("모집중"),
    END("모집완료");
    private final String description;
}
