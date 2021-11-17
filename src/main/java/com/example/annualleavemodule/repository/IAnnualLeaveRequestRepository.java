package com.example.annualleavemodule.repository;

import com.example.annualleavemodule.entity.AnnualLeaveRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IAnnualLeaveRequestRepository extends JpaRepository<AnnualLeaveRequest, Long> {

  @Query("select alr from AnnualLeaveRequest alr where alr.annualLeaveStatus = 'PENDING' ")
  List<AnnualLeaveRequest> getPendingRequests();

  @Modifying(clearAutomatically = true)
  @Query(
      "update AnnualLeaveRequest al set al.annualLeaveStatus = 'REJECTED' where al.id = :annualLeaveRequestId and al"
      + ".annualLeaveStatus = 'PENDING' ")
  void rejectAnnualLeaveRequest(Long annualLeaveRequestId);

  @Modifying(clearAutomatically = true)
  @Query(
      "update AnnualLeaveRequest al set al.annualLeaveStatus = 'APPROVED' where al.id = :annualLeaveRequestId and al"
      + ".annualLeaveStatus = 'PENDING' ")
  void approveAnnualLeaveRequest(Long annualLeaveRequestId);
}
