package com.example.annualleavemodule.controller.admin;

import com.example.annualleavemodule.entity.AnnualLeaveRequest;
import com.example.annualleavemodule.service.IAnnualLeaveService;
import java.util.List;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@Api(value="Admin Rest Service")
public class AdminController {

  private final IAnnualLeaveService iAnnualLeaveService;

  public AdminController(IAnnualLeaveService iAnnualLeaveService) {
    this.iAnnualLeaveService = iAnnualLeaveService;
  }

  @GetMapping("/get-pendings")
  public List<AnnualLeaveRequest> getPendingRequests() {
    return iAnnualLeaveService.getPendingRequests();
  }

  @PostMapping("/{annualLeaveReqId}/reject")
  public void rejectAnnualLeaveRequest(@PathVariable Long annualLeaveReqId) {
    iAnnualLeaveService.rejectAnnualLeaveRequest(annualLeaveReqId);
  }

  @PostMapping("/{annualLeaveReqId}/approve")
  public void approveAnnualLeaveRequest(@PathVariable Long annualLeaveReqId) {
    iAnnualLeaveService.approveAnnualLeaveRequest(annualLeaveReqId);
  }
}
