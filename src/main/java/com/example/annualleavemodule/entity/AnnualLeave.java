package com.example.annualleavemodule.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "annual_leave")
@ApiModel(value="Annual Leave",description="Model")
public class AnnualLeave {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @ApiModelProperty(value="Annual Leave ID")
  private Long id;

  @Column(name = "annual_leave_left")
  @ApiModelProperty(value="Annual Leave Left")
  private Integer annualLeaveLeft;

  @OneToOne(mappedBy = "annualLeave")
  @ApiModelProperty(value="Employee")
  private Employee employee;

  public AnnualLeave() {

  }

  public AnnualLeave(Integer annualLeaveLeft) {
    this.annualLeaveLeft = annualLeaveLeft;
  }

  public Long getId() {
    return id;
  }

  public Integer getAnnualLeaveLeft() {
    return annualLeaveLeft;
  }

  public void setAnnualLeaveLeft(Integer annualLeaveLeft) {
    this.annualLeaveLeft = annualLeaveLeft;
  }
}
