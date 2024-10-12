package com.cbidici.site.quote;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;

@Profile("unit")
class QuoteServiceTest {

  private QuoteService service;

  @BeforeEach
  void beforeEach() {
    service = new QuoteService();
  }

  @Test
  void testReturnsRandomQuote() {
    assertThat(service.getRandom().quote()).isNotEmpty();
    assertThat(service.getRandom().owner()).isNotEmpty();
  }
}