package com.example.annualleavemodule.config;

import com.example.annualleavemodule.repository.IAnnualLeaveRepository;
import com.example.annualleavemodule.repository.IAnnualLeaveRequestRepository;
import com.example.annualleavemodule.repository.IEmployeeRepository;
import com.example.annualleavemodule.repository.IUserRoleRepository;
import com.example.annualleavemodule.service.AnnualLeaveServiceImpl;
import com.example.annualleavemodule.service.EmployeeServiceImpl;
import com.example.annualleavemodule.service.IAnnualLeaveService;
import com.example.annualleavemodule.service.IEmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnualLeaveAppConfig {

  @Bean
  public IEmployeeService iEmployeeService(IEmployeeRepository iEmployeeRepository,
                                           IUserRoleRepository iUserRoleRepository) {
    return new EmployeeServiceImpl(iEmployeeRepository, iUserRoleRepository);
  }

  @Bean
  public IAnnualLeaveService iAnnualLeaveService(IAnnualLeaveRequestRepository iAnnualLeaveRequestRepository,
                                                 IAnnualLeaveRepository iAnnualLeaveRepository) {
    return new AnnualLeaveServiceImpl(iAnnualLeaveRequestRepository, iAnnualLeaveRepository);
  }
}
