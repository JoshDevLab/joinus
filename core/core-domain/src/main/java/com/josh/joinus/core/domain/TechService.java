package com.josh.joinus.core.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TechService {
    private final TechWriter techWriter;

    public void add(String name) {
        techWriter.add(name);
    }
}
