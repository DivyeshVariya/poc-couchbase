package com.poc.couchbase.exceptions;

public class EmployeeNotFound extends RuntimeException{
  public EmployeeNotFound(String message) {
    super(message);
  }
}
