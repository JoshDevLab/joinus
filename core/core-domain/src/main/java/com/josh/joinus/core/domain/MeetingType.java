package com.josh.joinus.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MeetingType {
    PROJECT("프로젝트"),
    STUDY("스터디");

    private final String description;
}
