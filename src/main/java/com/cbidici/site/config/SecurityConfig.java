package com.cbidici.site.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(
            auth -> auth
                .requestMatchers("/", "/posts", "/post/{id}", "/posts/{id}",
                    "/posts/*/{id}", "/home", "/about", "/css/**", "/js/**", "/images/**",
                    "/error", "/robots.txt")
                .permitAll()
                .anyRequest().authenticated()
        ).formLogin(login -> login
            .defaultSuccessUrl("/")
            .permitAll()
            .loginPage("/login")
        ).logout(login -> login.logoutSuccessUrl("/"));
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}