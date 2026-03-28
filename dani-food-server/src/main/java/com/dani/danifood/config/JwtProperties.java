package com.dani.danifood.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "dani.jwt")
@Data
public class JwtProperties {
    private String secretKey;
    private long ttl;
    private String employeeTokenName;
}