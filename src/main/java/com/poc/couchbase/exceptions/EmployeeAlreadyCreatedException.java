package com.poc.couchbase.exceptions;

public class EmployeeAlreadyCreatedException extends RuntimeException{
  public EmployeeAlreadyCreatedException(String message) {
    super(message);
  }
}
