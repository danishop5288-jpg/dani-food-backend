package com.dani.danifood;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class PasswordTest {
@Test
public void generateHash() {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // The plain text password Employee used
    String rawPassword = "666666";
    String encodedPassword = encoder.encode(rawPassword);

    System.out.println("========================================");
    System.out.println("Copy the following hash to your DB:");
    System.out.println(encodedPassword);
    System.out.println("========================================");
}
}
