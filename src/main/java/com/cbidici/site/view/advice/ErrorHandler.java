package com.cbidici.site.view.advice;

import com.cbidici.site.media.StorageFileNotFoundException;
import com.cbidici.site.post.PostNotFoundException;
import com.cbidici.site.view.data.ErrorDetailResponse;
import java.nio.file.AccessDeniedException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

  private final static String ERROR_VIEW = "error";

  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ModelAndView handleBadRequest(Exception e) {
    return new ModelAndView(
        ERROR_VIEW,
        Map.of(
            "errorDetail",
            new ErrorDetailResponse(HttpStatus.BAD_REQUEST, "Looks like something went wrong.")
        )
    );
  }

  @ExceptionHandler({ AccessDeniedException.class, AuthenticationException.class, InsufficientAuthenticationException.class })
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ModelAndView handleAuthenticationException(Exception ex) {
    return new ModelAndView(
        ERROR_VIEW,
        Map.of(
            "errorDetail",
            new ErrorDetailResponse(HttpStatus.UNAUTHORIZED, "Ups! Looks like you are lost.")
        )
    );
  }

  @ExceptionHandler({AuthorizationDeniedException.class})
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ModelAndView handleAuthorizationException(Exception ex) {
    return new ModelAndView(
        ERROR_VIEW,
        Map.of(
            "errorDetail",
            new ErrorDetailResponse(HttpStatus.FORBIDDEN, "Ups! Looks like you are lost.")
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

  @ExceptionHandler(StorageFileNotFoundException.class)
  public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
    return ResponseEntity.notFound().build();
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
