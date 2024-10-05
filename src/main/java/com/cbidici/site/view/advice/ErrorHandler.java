package com.cbidici.site.view.advice;

import com.cbidici.site.post.PostNotFoundException;
import com.cbidici.site.view.data.ErrorDetailResponse;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

  private final static String ERROR_VIEW = "error";

  @ExceptionHandler({ AuthenticationException.class })
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ModelAndView handleAuthenticationException(Exception ex) {
    return new ModelAndView(
        ERROR_VIEW,
        Map.of(
            "errorDetail",
            new ErrorDetailResponse(HttpStatus.FORBIDDEN, "Ups! Looks like your are lost.")
        )
    );
  }

  @ExceptionHandler({AuthorizationDeniedException.class})
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ModelAndView handleAuthorizationException(Exception ex) {
    return new ModelAndView(
        ERROR_VIEW,
        Map.of(
            "errorDetail",
            new ErrorDetailResponse(HttpStatus.UNAUTHORIZED, "Ups! Looks like your are lost.")
        )
    );
  }

  @ExceptionHandler({NoResourceFoundException.class, PostNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ModelAndView notFound(Exception ex) {
    return new ModelAndView(
        ERROR_VIEW,
        Map.of(
            "errorDetail",
            new ErrorDetailResponse(HttpStatus.NOT_FOUND, "Sorry! The page that you are looking for is not available.")
        )
    );
  }

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ModelAndView defaultHandler(Exception ex) {
    log.error("Exception Occurred", ex);
    return new ModelAndView(
        ERROR_VIEW,
        Map.of(
            "errorDetail",
            new ErrorDetailResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Sorry! An unexpected error occurred. Please try again later.")
        )
    );
  }
}
