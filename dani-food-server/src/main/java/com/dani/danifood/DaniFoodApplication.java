package com.dani.danifood;

import com.dani.danifood.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
/*
Spring Boot can only scan components within the current module by default.Therefore,
I need to add the @EntityScan annotation to explicitly tell Spring Boot where to find the entity classes.
 */
@EntityScan("com.dani.danifood.entity")
public class DaniFoodApplication {
    public static void main(String[] args) {
        SpringApplication.run(DaniFoodApplication.class, args);
    }

}

