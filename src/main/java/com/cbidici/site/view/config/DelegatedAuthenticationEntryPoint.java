package com.cbidici.site.view.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;

@Component
@Slf4j
public class DelegatedAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final HandlerExceptionResolver exceptionResolver;
  private final ViewResolver viewResolver;

  public DelegatedAuthenticationEntryPoint(
      @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver,
      ViewResolver viewResolver) {
    this.exceptionResolver = exceptionResolver;
    this.viewResolver = viewResolver;
  }

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
    var resolvedModelAndView = exceptionResolver.resolveException(request, response, null, authException);
    try {
      var resolvedView = viewResolver.resolveViewName(resolvedModelAndView.getViewName(), Locale.getDefault());
      resolvedView.render(resolvedModelAndView.getModel(), request, response);
    } catch (Exception e) {
      log.error("Error occurred : ", e);
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }
}
