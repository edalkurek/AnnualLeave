package com.example.annualleavemodule.bean;

import com.example.annualleavemodule.enums.UserType;
import java.time.LocalDate;


public class EmployeeCreateRequest {

  private String name;

  private String surname;

  private String email;

  private String password;

  private UserType userType;

  private LocalDate startedAt;

  public EmployeeCreateRequest() {
  }

  public EmployeeCreateRequest(String name, String surname, String email, String password, UserType userType, LocalDate startedAt) {
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.password = password;
    this.userType = userType;
    this.startedAt = startedAt;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }

  public LocalDate getStartedAt() {
    return startedAt;
  }

  public void setStartedAt(LocalDate startedAt) {
    this.startedAt = startedAt;
  }
}
