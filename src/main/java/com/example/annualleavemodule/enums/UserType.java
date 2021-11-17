package com.example.annualleavemodule.enums;

public enum UserType {
  ROLE_USER(1),
  ROLE_ADMIN(2);

  private final Integer id;

  UserType(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }
}
