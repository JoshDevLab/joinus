package com.josh.joinus.core.api.controller;

import com.josh.joinus.core.domain.Tech;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TechResponse {
    private String name;
    private Long id;

    public static TechResponse create(Tech tech) {
        return TechResponse.builder()
                .id(tech.getId())
                .name(tech.getName())
                .build();
    }


}
