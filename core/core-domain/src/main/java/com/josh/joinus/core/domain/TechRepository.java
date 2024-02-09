package com.josh.joinus.core.domain;

import java.util.List;

public interface TechRepository {
    Tech add(String name);
    List<Tech> findAll();
    List<Tech> findByTechIdIn(List<Long> techIdList);
}
