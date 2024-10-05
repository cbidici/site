package com.cbidici.site.view.data;

import java.util.Optional;
import java.util.Set;
import lombok.Builder;
import org.springframework.security.web.csrf.CsrfToken;

@Builder
public class Page {
  private String requestURI;
  private boolean authenticated;
  private Set<String> authorities;
  private CsrfToken crsf;

  public boolean isAuthenticated() {
    return this.authenticated;
  }

  public boolean hasRole(String role) {
    return Optional.ofNullable(this.authorities)
        .map(s -> s.contains("ROLE_"+role))
        .orElse(false);
  }

  public String getCsrfToken() {
    return Optional.ofNullable(crsf).map(CsrfToken::getToken).orElse("");
  }

  public boolean isCurrentUri(String requestURI) {
    return Optional.ofNullable(this.requestURI).map(u -> u.equals(requestURI)).orElse(false);
  }
}
