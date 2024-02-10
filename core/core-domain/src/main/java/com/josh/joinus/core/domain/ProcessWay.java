package com.josh.joinus.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProcessWay {
    OFFLINE("오프라인"),
    ONLINE("온라인"),
    ONOFFLINE("온/오프라인");
    private final String description;
}
