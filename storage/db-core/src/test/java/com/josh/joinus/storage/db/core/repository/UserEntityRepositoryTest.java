package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.Tech;
import com.josh.joinus.core.domain.TechRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
class UserEntityRepositoryTest {
    @Autowired
    TechEntityRepository techEntityRepository;

    @Test
    void add() {
        //given
        String name = "Spring boot";

        //when
        techEntityRepository.add(name);
        List<Tech> result = techEntityRepository.findAll();

        //then
        Assertions.assertThat(result).hasSize(1)
                .extracting("id", "name")
                .containsExactlyInAnyOrder(tuple(1L, "Spring boot"));
    }
}