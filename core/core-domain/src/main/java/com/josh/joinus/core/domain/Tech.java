package com.josh.joinus.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Tech extends Base {
    private Long id;
    private String name;
    private String techImg;

    @Builder
    private Tech(Long id, String name, String techImg) {
        this.id = id;
        this.name = name;
        this.techImg = techImg;
    }
}
