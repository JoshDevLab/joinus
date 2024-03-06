package com.josh.joinus.core.domain.meeting;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JoinStatus {
    WAITING("대기"),
    ACCEPT("수락"),
    REFUSE("거절");
    private final String description;
}
