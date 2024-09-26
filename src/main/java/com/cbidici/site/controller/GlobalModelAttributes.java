package com.cbidici.site.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {

  @ModelAttribute("requestURI")
  public String requestUri(HttpServletRequest request) {
    return request.getRequestURI();
  }
}
