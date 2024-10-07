package com.cbidici.site.view.config;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SecurityConfig {

  private final DelegatedAuthenticationEntryPoint authenticationEntryPoint;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(
            auth -> auth
                .requestMatchers("/posts/create", "/posts/*/update", "/post/*/publish",
                    "/posts/*/withdraw", "/posts/*/delete", "/storage")
                .authenticated()
                .anyRequest().permitAll()
        )
        .formLogin(login -> login
            .defaultSuccessUrl("/")
            .permitAll()
            .loginPage("/login")
        )
        .logout(login -> login.logoutSuccessUrl("/"))
        .exceptionHandling(eh -> eh.authenticationEntryPoint(authenticationEntryPoint));
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}