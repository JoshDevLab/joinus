package com.josh.joinus.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class Position {
    private Long id;
    private String name;

    public Position(String name) {
        this.name = name;
    }

    @Builder
    private Position(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
