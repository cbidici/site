package com.cbidici.site.config;

import com.github.slugify.Slugify;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public Slugify getSlugify() {
    return Slugify.builder().build();
  }
}
