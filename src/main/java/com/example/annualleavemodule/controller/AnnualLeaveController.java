package com.example.annualleavemodule.controller;

import com.example.annualleavemodule.bean.AnnualLeaveCreateRequest;
import com.example.annualleavemodule.bean.AnnualLeaveCreateResponse;
import com.example.annualleavemodule.exception.AnnualLeaveLeftException;
import com.example.annualleavemodule.service.IAnnualLeaveService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Api(value="Annual Leave Rest Service")
public class AnnualLeaveController {

  private final IAnnualLeaveService iAnnualLeaveService;

  public AnnualLeaveController(IAnnualLeaveService iAnnualLeaveService) {
    this.iAnnualLeaveService = iAnnualLeaveService;
  }

  @PostMapping("/{employeeId}/leave-request")
  public AnnualLeaveCreateResponse createAnnualLeaveRequest(
      @RequestBody AnnualLeaveCreateRequest annualLeaveCreateRequest,
      @PathVariable Long employeeId) throws AnnualLeaveLeftException {
    return iAnnualLeaveService.createAnnualLeaveRequest(annualLeaveCreateRequest, employeeId);
  }
}
