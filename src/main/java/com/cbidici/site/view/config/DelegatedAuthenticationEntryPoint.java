package com.cbidici.site.view.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;

@Component
public class DelegatedAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Autowired
  @Qualifier("handlerExceptionResolver")
  private HandlerExceptionResolver resolver;

  @Autowired
  ViewResolver viewResolver;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
    var val = resolver.resolveException(request, response, null, authException);
    try {
      var resolved = viewResolver.resolveViewName(val.getViewName(), Locale.getDefault());
      resolved.render(val.getModel(), request, response);
      //response.sendError(HttpStatus.FORBIDDEN.value(), "Forbidden");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
