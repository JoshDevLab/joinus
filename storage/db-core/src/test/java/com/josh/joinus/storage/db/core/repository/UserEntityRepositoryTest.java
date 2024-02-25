package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.Tech;
import com.josh.joinus.core.domain.TechRepository;
import com.josh.joinus.storage.db.core.CoreDbContextTest;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
class UserEntityRepositoryTest extends CoreDbContextTest {
    @Autowired
    TechEntityRepository techEntityRepository;

    @Test
    void add() {
        //given
        String name = "Spring boot";

        //when
        Tech result = techEntityRepository.add(name);

        //then
        Assertions.assertThat(result.getName()).isEqualTo("Spring boot");
    }
}