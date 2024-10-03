package com.poc.couchbase.exceptions.handler;

import com.poc.couchbase.dto.response.Response;
import com.poc.couchbase.exceptions.AddressNotFoundException;
import com.poc.couchbase.exceptions.EmployeeAlreadyCreatedException;
import com.poc.couchbase.exceptions.EmployeeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.util.HashMap;
import java.util.Map;

import static com.poc.couchbase.constants.Constants.CLASS;
import static com.poc.couchbase.constants.Constants.METHOD;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EmployeeNotFoundException.class)
  public ResponseEntity<Response> handleEmployeeNotFoundException(EmployeeNotFoundException ex, HandlerMethod handlerMethod) {
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

  @ExceptionHandler(AddressNotFoundException.class)
  public ResponseEntity<Response> handleAddressNotFoundException(AddressNotFoundException ex, HandlerMethod handlerMethod) {
    log.error("Address not found: {}", ex.getMessage());
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

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Response> handleValidationException(
          MethodArgumentNotValidException ex, HandlerMethod handlerMethod) {
    Map<String, String> errors = new HashMap<>();
    ex
            .getBindingResult()
            .getAllErrors()
            .forEach(
                    error -> {
                      String fieldName = ((FieldError) error).getField();
                      String errorMessage =
                              error.getDefaultMessage(); // This will contain your custom message
                      errors.put(fieldName, errorMessage);
                    });
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                    Response
                            .builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .data(
                                    Map.of(
                                            CLASS,
                                            handlerMethod
                                                    .getBeanType()
                                                    .getSimpleName(),
                                            METHOD,
                                            handlerMethod
                                                    .getMethod()
                                                    .getName(),
                                            "Error",
                                            errors))
                            .build());
  }

  @ExceptionHandler(EmployeeAlreadyCreatedException.class)
  public ResponseEntity<Response> handleEmployeeAlreadyCreatedException(EmployeeAlreadyCreatedException ex,
                                                                        HandlerMethod handlerMethod) {
    log.error("Employee already present : {}", ex.getMessage());
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                    Response
                            .builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
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
