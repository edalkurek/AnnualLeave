package com.example.annualleavemodule.service;

import com.example.annualleavemodule.bean.AnnualLeaveCreateRequest;
import com.example.annualleavemodule.bean.AnnualLeaveCreateResponse;
import com.example.annualleavemodule.entity.AnnualLeaveRequest;
import com.example.annualleavemodule.exception.AnnualLeaveLeftException;
import com.example.annualleavemodule.repository.IAnnualLeaveRepository;
import com.example.annualleavemodule.repository.IAnnualLeaveRequestRepository;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import static com.example.annualleavemodule.enums.AnnualLeaveStatus.PENDING;

public class AnnualLeaveServiceImpl implements IAnnualLeaveService {

  private final IAnnualLeaveRequestRepository iAnnualLeaveRequestRepository;

  private final IAnnualLeaveRepository iAnnualLeaveRepository;

  public AnnualLeaveServiceImpl(IAnnualLeaveRequestRepository iAnnualLeaveRequestRepository,
                                IAnnualLeaveRepository iAnnualLeaveRepository) {
    this.iAnnualLeaveRequestRepository = iAnnualLeaveRequestRepository;
    this.iAnnualLeaveRepository = iAnnualLeaveRepository;
  }

  @Override
  public AnnualLeaveCreateResponse createAnnualLeaveRequest(AnnualLeaveCreateRequest annualLeaveCreateRequest,
                                                            Long employeeId)
      throws AnnualLeaveLeftException {
    var requestedDate = annualLeaveCreateRequest.getRequestedDate();
    if (isRequestedDateValid(requestedDate, employeeId)) {
      var annualLeaveRequest = new AnnualLeaveRequest(employeeId, requestedDate, PENDING);
      return new AnnualLeaveCreateResponse(iAnnualLeaveRequestRepository.save(annualLeaveRequest).getId());
    } else {
      throw new AnnualLeaveLeftException(requestedDate);
    }
  }

  @Override
  public List<AnnualLeaveRequest> getPendingRequests() {
    return iAnnualLeaveRequestRepository.getPendingRequests();
  }

  @Override
  @Transactional
  public void approveAnnualLeaveRequest(Long annualLeaveRequestId) {
    iAnnualLeaveRequestRepository.approveAnnualLeaveRequest(annualLeaveRequestId);
    var annualLeave = iAnnualLeaveRequestRepository.findById(annualLeaveRequestId)
                                                   .orElse(null);
    var annualLeaveLeft = getAnnualLeaveLeft(annualLeave.getEmployeeId(), annualLeave.getRequestedDate());

    iAnnualLeaveRepository.updateAnnualLeave(annualLeave.getEmployeeId(), annualLeaveLeft);
  }

  @Override
  @Transactional
  public void rejectAnnualLeaveRequest(Long annualLeaveRequestId) {
    iAnnualLeaveRequestRepository.rejectAnnualLeaveRequest(annualLeaveRequestId);
  }

  private boolean isRequestedDateValid(Integer requestedDate, Long employeeId) {
    if (requestedDate <= iAnnualLeaveRepository.getAnnualLeaveLeft(employeeId)) {
      return true;
    } else {
      return false;
    }
  }

  private Integer getAnnualLeaveLeft(Long employeeId, Integer requestedDate) {
    return iAnnualLeaveRepository.getAnnualLeaveLeft(employeeId) - requestedDate;
  }
}