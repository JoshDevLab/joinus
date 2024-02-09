package com.josh.joinus.core.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TechWriter {

    private final TechRepository techRepository;

    public Tech add(String name) {
       return techRepository.add(name);
    }
}
