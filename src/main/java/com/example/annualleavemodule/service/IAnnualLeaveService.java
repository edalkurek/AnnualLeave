package com.example.annualleavemodule.service;

import com.example.annualleavemodule.bean.AnnualLeaveCreateRequest;
import com.example.annualleavemodule.bean.AnnualLeaveCreateResponse;
import com.example.annualleavemodule.entity.AnnualLeaveRequest;
import com.example.annualleavemodule.exception.AnnualLeaveLeftException;
import java.util.List;

public interface IAnnualLeaveService {

  AnnualLeaveCreateResponse createAnnualLeaveRequest(AnnualLeaveCreateRequest annualLeaveCreateRequest,
                                                     Long employeeId) throws AnnualLeaveLeftException;

  List<AnnualLeaveRequest> getPendingRequests();

  void rejectAnnualLeaveRequest(Long annualLeaveRequestId);

  void approveAnnualLeaveRequest(Long annualLeaveRequestId);
}
