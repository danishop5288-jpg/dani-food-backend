package com.dani.danifood.config;

import com.dani.danifood.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /*1️⃣Spring Security not only provides encryption,
        but also automatically enables security interception for all requests.*/
    /*2️⃣Spring Security blocks all requests by default.
This config allows the login endpoint to be accessed without authentication.*/
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .formLogin(formLogin -> formLogin.disable())
                    .httpBasic(httpBasicAuth -> httpBasicAuth.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(HttpMethod.POST,"/employee/api/login").permitAll()
                            .requestMatchers("/employee/**").authenticated()
                            .anyRequest().permitAll()
                    )
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }

}
