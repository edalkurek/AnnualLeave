package com.example.annualleavemodule.bean;

import com.example.annualleavemodule.enums.UserType;
import java.time.LocalDate;
import java.util.Set;

public class EmployeeCreateRequest {

  private String name;

  private String surname;

  private String email;

  private String password;

  private Set<UserType> userType;

  private LocalDate startedAt;

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public Set<UserType> getUserType() {
    return userType;
  }

  public LocalDate getStartedAt() {
    return startedAt;
  }
}
