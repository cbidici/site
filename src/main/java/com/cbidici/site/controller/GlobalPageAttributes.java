package com.cbidici.site.controller;

import com.cbidici.site.controller.data.Page;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalPageAttributes {

  @ModelAttribute("requestURI")
  public String requestUri(HttpServletRequest request) {
    return request.getRequestURI();
  }

  @ModelAttribute("page")
  public Page page(HttpServletRequest request, Authentication authentication, Model model) {
    return new Page(
        request.getRequestURI(),
        authentication != null && authentication.isAuthenticated(),
        authentication == null? Set.of() : authentication.getAuthorities().stream().map(
            GrantedAuthority::getAuthority).collect(Collectors.toSet()),
        (CsrfToken) request.getAttribute("_csrf")
    );
  }
}
