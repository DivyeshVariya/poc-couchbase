package com.poc.couchbase.exceptions;

public class AddressNotFoundException extends RuntimeException{
  public AddressNotFoundException(String message) {
    super(message);
  }
}
