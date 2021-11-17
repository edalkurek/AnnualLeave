package com.example.annualleavemodule.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )

  @ApiModelProperty(value="User Role")
  private Set<UserRole> userRoles;

  public Employee() {

  }

  public Employee(String name, String surname, String email, String password, LocalDate startedAt,
                  AnnualLeave annualLeave, Set<UserRole> userRoles) {
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.password = password;
    this.startedAt = startedAt;
    this.annualLeave = annualLeave;
    this.userRoles = userRoles;
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

  public Set<UserRole> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(Set<UserRole> userRoles) {
    this.userRoles = userRoles;
  }
}
