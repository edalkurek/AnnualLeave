package com.example.annualleavemodule.service;

import com.example.annualleavemodule.bean.EmployeeCreateRequest;
import com.example.annualleavemodule.bean.EmployeeCreateResponse;
import com.example.annualleavemodule.entity.AnnualLeave;
import com.example.annualleavemodule.entity.Employee;
import com.example.annualleavemodule.enums.UserType;
import com.example.annualleavemodule.exception.EmployeeCreatedBeforeException;
import com.example.annualleavemodule.repository.IEmployeeRepository;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EmployeeServiceImpl implements IEmployeeService {

  private final IEmployeeRepository iEmployeeRepository;

  public EmployeeServiceImpl(IEmployeeRepository iEmployeeRepository) {
    this.iEmployeeRepository = iEmployeeRepository;
  }

  @Override
  public EmployeeCreateResponse createEmployee(EmployeeCreateRequest employeeCreateRequest) throws EmployeeCreatedBeforeException {
    String email = employeeCreateRequest.getEmail();
    if (isValid(email)) {
      throw new EmployeeCreatedBeforeException(email);
    }

    Employee employee = toEmployee(employeeCreateRequest);
    iEmployeeRepository.save(employee);

    return new EmployeeCreateResponse(employee.getId());
  }

  private Employee toEmployee(EmployeeCreateRequest employeeCreateRequest) {
    LocalDate startedAt = employeeCreateRequest.getStartedAt();
    startedAt = (startedAt != null) ? startedAt : LocalDate.now();

    AnnualLeave annualLeave = new AnnualLeave(getLeavePeriod(startedAt));
    UserType userType = employeeCreateRequest.getUserType();

    return new Employee(
            employeeCreateRequest.getName(),
            employeeCreateRequest.getSurname(),
            employeeCreateRequest.getEmail(),
            getEncodedPassword(employeeCreateRequest.getPassword()),
            startedAt,
            annualLeave,
            userType
    );
  }

  private String getEncodedPassword(String password) {
    return new BCryptPasswordEncoder().encode(password);
  }



  private boolean isValid(String email) {
    return iEmployeeRepository.findEmployeeByEmail(email).isPresent();
  }

  private int getLeavePeriod(LocalDate startedAt) {
    int yearsOfExperience = calculateExperienceYears(startedAt);

    if (yearsOfExperience == 0) {
      return 5;
    } else if (yearsOfExperience <= 5) {
      return 15;
    } else if (yearsOfExperience <= 10) {
      return 18;
    } else {
      return 24;
    }
  }

  private int calculateExperienceYears(LocalDate startedAt) {
    Period period = Period.between(startedAt, LocalDate.now());
    return period.getYears();
  }
}
