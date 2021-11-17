package com.example.annualleavemodule.exception;

public class AnnualLeaveLeftException extends Exception {

  private final static String DEFAULT_MESSAGE = "You are not entitled to annual leave within the requested period: ";

  public AnnualLeaveLeftException(Integer requestedDate) {
    super(String.format("%s%d", DEFAULT_MESSAGE, requestedDate));
  }
}
