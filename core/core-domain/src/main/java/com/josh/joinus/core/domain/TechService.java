package com.josh.joinus.core.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TechService {
    private final TechWriter techWriter;

    public Tech add(String name) {
        return techWriter.add(name);
    }
}
