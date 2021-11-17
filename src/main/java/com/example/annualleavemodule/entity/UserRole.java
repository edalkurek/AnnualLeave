package com.example.annualleavemodule.entity;

import com.example.annualleavemodule.enums.UserType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
@ApiModel(value="User Role",description="Model")
public class UserRole {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  @ApiModelProperty(value="Role Id")
  private Long id;

  @Column(name = "user_type")
  @Enumerated(value = EnumType.STRING)
  @ApiModelProperty(value="User Type")
  private UserType userType;

  public UserRole() {

  }

  public UserRole(UserType userType) {
    this.userType = userType;
  }

  public Long getId() {
    return id;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }
}
