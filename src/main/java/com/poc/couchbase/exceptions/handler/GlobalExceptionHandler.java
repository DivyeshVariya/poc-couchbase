package com.poc.couchbase.exceptions.handler;

import com.poc.couchbase.dto.response.Response;
import com.poc.couchbase.exceptions.EmployeeNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final String CLASS = "class";
  private static final String METHOD = "method";

  @ExceptionHandler(EmployeeNotFound.class)
  public ResponseEntity<Response> handleEmployeeNotFoundException(EmployeeNotFound ex, HandlerMethod handlerMethod) {
    log.error("Employee not found: {}", ex.getMessage());
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                    Response
                            .builder()
                            .status(HttpStatus.NOT_FOUND)
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .message(ex.getMessage())
                            .data(Map.of(CLASS,
                                    handlerMethod
                                            .getBeanType()
                                            .getSimpleName(), METHOD,
                                    handlerMethod
                                            .getMethod()
                                            .getName()))
                            .build());
  }
}
