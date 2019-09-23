package com.stackroute.squad.exceptions;

public class ServiceProviderNotFoundException  extends Exception {
  private String message;

  public ServiceProviderNotFoundException() {
  }

  public ServiceProviderNotFoundException(String message) {
    super(message);
    this.message = message;
  }

}
