package com.example.annualleavemodule.service;

import com.example.annualleavemodule.bean.AnnualLeaveCreateRequest;
import com.example.annualleavemodule.bean.AnnualLeaveCreateResponse;
import com.example.annualleavemodule.entity.AnnualLeaveRequest;
import com.example.annualleavemodule.exception.AnnualLeaveLeftException;
import com.example.annualleavemodule.repository.IAnnualLeaveRepository;
import com.example.annualleavemodule.repository.IAnnualLeaveRequestRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.annualleavemodule.enums.AnnualLeaveStatus.PENDING;

@Service
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
                                                            Long employeeId) throws AnnualLeaveLeftException {
    LocalDate startDate = annualLeaveCreateRequest.getStartDate();
    LocalDate endDate = annualLeaveCreateRequest.getEndDate();

    int annualLeaveDays = calculateAnnualLeaveDays(startDate, endDate);

    if (!isRequestedDateValid(annualLeaveDays, employeeId)) {
      throw new AnnualLeaveLeftException(annualLeaveDays);
    }

    AnnualLeaveRequest annualLeaveRequest = new AnnualLeaveRequest(employeeId, annualLeaveDays, PENDING);
    iAnnualLeaveRequestRepository.save(annualLeaveRequest);

    // Return the annual leave request ID
    return new AnnualLeaveCreateResponse(annualLeaveRequest.getId());
  }

  @Override
  public List<AnnualLeaveRequest> getPendingRequests() {
    return iAnnualLeaveRequestRepository.getPendingRequests();
  }

  @Override
  @Transactional
  public void approveAnnualLeaveRequest(Long annualLeaveRequestId) {
    iAnnualLeaveRequestRepository.approveAnnualLeaveRequest(annualLeaveRequestId);
    AnnualLeaveRequest annualLeave = iAnnualLeaveRequestRepository.findById(annualLeaveRequestId)
                                                   .orElse(null);
    Integer annualLeaveLeft = getAnnualLeaveLeft(annualLeave.getEmployeeId(), annualLeave.getRequestedDate());

    iAnnualLeaveRepository.updateAnnualLeave(annualLeave.getEmployeeId(), annualLeaveLeft);
  }

  @Override
  @Transactional
  public void rejectAnnualLeaveRequest(Long annualLeaveRequestId) {
    iAnnualLeaveRequestRepository.rejectAnnualLeaveRequest(annualLeaveRequestId);
  }

  private boolean isRequestedDateValid(Integer requestedDate, Long employeeId) {
    return requestedDate <= iAnnualLeaveRepository.getAnnualLeaveLeft(employeeId);
  }

  private Integer getAnnualLeaveLeft(Long employeeId, Integer requestedDate) {
    return iAnnualLeaveRepository.getAnnualLeaveLeft(employeeId) - requestedDate;
  }

  private int calculateAnnualLeaveDays(LocalDate startDate, LocalDate endDate) {
    int workingDays = 0;
    LocalDate currentDate = startDate;

    while (!currentDate.isAfter(endDate)) {
      if (currentDate.getDayOfWeek() != DayOfWeek.SATURDAY && currentDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
        workingDays++;
      }
      currentDate = currentDate.plusDays(1);
    }

    return workingDays;
  }

}