package com.example.annualleavemodule.repository;

import com.example.annualleavemodule.entity.AnnualLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IAnnualLeaveRepository extends JpaRepository<AnnualLeave, Long> {

  @Query("select al.annualLeaveLeft "
         + "from AnnualLeave al "
         + "inner join Employee e "
         + "on e.annualLeave.id = al.id "
         + "where e.id = :employeeId")
  Integer getAnnualLeaveLeft(Long employeeId);

  @Modifying
  @Query("update AnnualLeave al set al.annualLeaveLeft = :annualLeaveLeft where al.id = :employeeId")
  void updateAnnualLeave(Long employeeId, Integer annualLeaveLeft);
}
