package com.josh.joinus.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Position {

    BACK_END("백엔드"),
    FRONT_END("프론트엔드"),
    FULL_STACK("풀스택"),
    DESIGNER("디자이너"),
    PRODUCT_MANAGER("기획자");

    private final String description;
}
