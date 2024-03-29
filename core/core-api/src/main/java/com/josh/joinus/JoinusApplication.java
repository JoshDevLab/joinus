package com.josh.joinus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class JoinusApplication {

    public static void main(String[] args) {
        SpringApplication.run(JoinusApplication.class, args);
    }

}
