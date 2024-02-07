package com.josh.joinus.core.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface TechRepository {
    void add(String name);
    List<Tech> findAll();
}
