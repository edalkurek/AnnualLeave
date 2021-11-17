package com.example.annualleavemodule.exception;

public class EmployeeCreatedBeforeException extends Exception {

  private static final String DEFAULT_MESSAGE = "Employee created before with unique email: ";

  public EmployeeCreatedBeforeException(String email) {
    super(String.format("%s%s", DEFAULT_MESSAGE, email));
  }
}
