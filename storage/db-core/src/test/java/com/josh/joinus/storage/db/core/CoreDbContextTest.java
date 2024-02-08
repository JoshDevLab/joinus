package com.josh.joinus.storage.db.core;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

@ActiveProfiles("local")
@Tag("context")
@SpringBootTest
public abstract class CoreDbContextTest {

}
