package com.example.annualleavemodule.bean;

public class EmployeeCreateResponse {

  private static final String DEFAULT_MESSAGE = "Employee created";

  private Long employeeId;

  private String message = DEFAULT_MESSAGE;

  public EmployeeCreateResponse(Long employeeId) {
    this.employeeId = employeeId;
  }

  public EmployeeCreateResponse(Long employeeId, String message) {
    this.employeeId = employeeId;
    this.message = message;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
