package com.josh.joinus.core.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TechReader {
    private final TechRepository techRepository;

    public List<Tech> findByTechIdIn(List<Long> techIdList) {
        return techRepository.findByTechIdIn(techIdList);
    }
}
