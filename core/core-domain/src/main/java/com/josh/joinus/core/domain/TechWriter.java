package com.josh.joinus.core.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TechWriter {

    private final TechRepository techRepository;

    public void add(String name) {
        techRepository.add(name);
    }
}
