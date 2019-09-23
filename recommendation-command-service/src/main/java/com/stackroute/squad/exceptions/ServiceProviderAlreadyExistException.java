package com.stackroute.squad.exceptions;
/**
 * Custom Exception to throw if Service provider Not Found
 */
public class ServiceProviderAlreadyExistException extends Exception {
  private String message;

  public ServiceProviderAlreadyExistException() {
  }

  public ServiceProviderAlreadyExistException(String message) {
    super(message);
    this.message = message;
  }

}
