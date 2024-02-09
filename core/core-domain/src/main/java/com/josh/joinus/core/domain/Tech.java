package com.josh.joinus.core.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Tech extends Base {
    private Long id;
    private String name;

    @Builder
    private Tech(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
