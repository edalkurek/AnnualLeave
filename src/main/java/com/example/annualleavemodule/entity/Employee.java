package com.example.annualleavemodule.entity;

import com.example.annualleavemodule.enums.UserType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "employee")
@ApiModel(value="Employee",description="Model")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  @ApiModelProperty(value="User ID")
  private Long id;

  @ApiModelProperty(value="User Name")
  @Column(name = "name")
  private String name;

  @ApiModelProperty(value="User Surname")
  @Column(name = "surname")
  private String surname;

  @ApiModelProperty(value="User Mail")
  @Column(name = "email")
  private String email;

  @ApiModelProperty(value="Password")
  @Column(name = "password")
  private String password;

  @ApiModelProperty(value="started date")
  @Column(name = "started_at")
  private LocalDate startedAt;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "annual_leave_id")
  @ApiModelProperty(value="Annual Leave")
  private AnnualLeave annualLeave;

  @Enumerated(EnumType.STRING)
  private UserType userType;

  public Employee() {

  }

  public Employee(String name, String surname, String email, String password, LocalDate startedAt,
                  AnnualLeave annualLeave, UserType userType) {
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.password = password;
    this.startedAt = startedAt;
    this.annualLeave = annualLeave;
    this.userType = userType;
  }

  public Long getId() {
    return id;
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

  public LocalDate getStartedAt() {
    return startedAt;
  }

  public void setStartedAt(LocalDate startedAt) {
    this.startedAt = startedAt;
  }

  public AnnualLeave getAnnualLeave() {
    return annualLeave;
  }

  public void setAnnualLeave(AnnualLeave annualLeave) {
    this.annualLeave = annualLeave;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }
}
