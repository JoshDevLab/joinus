dependencies {
    compileOnly ("jakarta.servlet:jakarta.servlet-api")
    compileOnly ("org.springframework.boot:spring-boot-starter-test")
    compileOnly ("com.fasterxml.jackson.core:jackson-databind")
    api ("org.springframework.restdocs:spring-restdocs-mockmvc")
    api ("org.springframework.restdocs:spring-restdocs-restassured")
    api ("io.rest-assured:spring-mock-mvc")
}
