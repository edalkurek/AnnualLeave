package com.example.annualleavemodule.entity;

import com.example.annualleavemodule.enums.AnnualLeaveStatus;
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
@Table(name = "annual_leave_request")
@ApiModel(value="Annual Leave Request",description="Model")
public class AnnualLeaveRequest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @ApiModelProperty(value="Annual Leave ID")
  private Long id;

  @Column(name = "employee_id")
  @ApiModelProperty(value="User ID")
  private Long employeeId;

  @Column(name = "requested_date")
  @ApiModelProperty(value="Request Date")
  private Integer requestedDate;

  @Column(name = "status")
  @Enumerated(value = EnumType.STRING)
  @ApiModelProperty(value="Annual Leave Status")
  private AnnualLeaveStatus annualLeaveStatus;

  public AnnualLeaveRequest() {

  }

  public AnnualLeaveRequest(Long employeeId, Integer requestedDate,
                            AnnualLeaveStatus annualLeaveStatus) {
    this.employeeId = employeeId;
    this.requestedDate = requestedDate;
    this.annualLeaveStatus = annualLeaveStatus;
  }

  public Long getId() {
    return id;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public Integer getRequestedDate() {
    return requestedDate;
  }

  public void setRequestedDate(Integer requestedDate) {
    this.requestedDate = requestedDate;
  }

  public AnnualLeaveStatus getAnnualLeaveStatus() {
    return annualLeaveStatus;
  }

  public void setAnnualLeaveStatus(AnnualLeaveStatus annualLeaveStatus) {
    this.annualLeaveStatus = annualLeaveStatus;
  }
}
