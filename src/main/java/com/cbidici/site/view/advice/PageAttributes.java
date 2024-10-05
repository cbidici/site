package com.cbidici.site.view.advice;

import com.cbidici.site.view.data.Page;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class PageAttributes {

  @ModelAttribute("page")
  public Page page(HttpServletRequest request, Authentication authentication) {
    return Page.builder()
        .requestURI(request.getRequestURI())
        .authenticated(authentication != null && authentication.isAuthenticated())
        .authorities(authentication == null? Set.of() : authentication.getAuthorities().stream().map(
            GrantedAuthority::getAuthority).collect(Collectors.toSet()))
        .crsf((CsrfToken) request.getAttribute("_csrf"))
        .build();
  }
}
