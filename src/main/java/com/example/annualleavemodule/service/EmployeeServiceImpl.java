package com.example.annualleavemodule.service;

import com.example.annualleavemodule.bean.EmployeeCreateRequest;
import com.example.annualleavemodule.bean.EmployeeCreateResponse;
import com.example.annualleavemodule.entity.AnnualLeave;
import com.example.annualleavemodule.entity.Employee;
import com.example.annualleavemodule.entity.UserRole;
import com.example.annualleavemodule.enums.UserType;
import com.example.annualleavemodule.exception.EmployeeCreatedBeforeException;
import com.example.annualleavemodule.repository.IEmployeeRepository;
import com.example.annualleavemodule.repository.IUserRoleRepository;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EmployeeServiceImpl implements IEmployeeService {

  private final IEmployeeRepository iEmployeeRepository;

  private final IUserRoleRepository iUserRoleRepository;

  public EmployeeServiceImpl(IEmployeeRepository iEmployeeRepository, IUserRoleRepository iUserRoleRepository) {
    this.iEmployeeRepository = iEmployeeRepository;
    this.iUserRoleRepository = iUserRoleRepository;
  }

  @Override
  public EmployeeCreateResponse createEmployee(EmployeeCreateRequest employeeCreateRequest)
      throws EmployeeCreatedBeforeException {
    var email = employeeCreateRequest.getEmail();
    if (isValid(email)) {
      var employee = toEmployee(employeeCreateRequest);
      iEmployeeRepository.save(employee);
      return new EmployeeCreateResponse(iEmployeeRepository.save(employee).getId());
    } else {
      throw new EmployeeCreatedBeforeException(email);
    }
  }

  private Employee toEmployee(EmployeeCreateRequest employeeCreateRequest) {
    var startedAt = employeeCreateRequest.getStartedAt();
    if (startedAt == null) {
      startedAt = LocalDate.now();
    }
    var annualLeave = new AnnualLeave(getLeavePeriod(startedAt));
    var userRole = getOrCreate(employeeCreateRequest.getUserType());
    return new Employee(employeeCreateRequest.getName(),
                        employeeCreateRequest.getSurname(),
                        employeeCreateRequest.getEmail(),
                        getEncodedPassword(employeeCreateRequest.getPassword()),
                        startedAt,
                        annualLeave,
                        userRole);
  }

  private String getEncodedPassword(String password) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return bCryptPasswordEncoder.encode(password);
  }

  private Set<UserRole> getOrCreate(Set<UserType> userTypes) {
    var userRoles = userTypes.stream()
                             .map(userType -> iUserRoleRepository.getUserRoleByName(userType)
                                                                 .orElse(new UserRole(userType)))
                             .collect(Collectors.toSet());

    return userRoles;
  }

  private boolean isValid(String email) {
    var employee = iEmployeeRepository.findEmployeeByEmail(email).orElse(null);
    if (employee != null) {
      return false;
    }
    return true;
  }

  private Integer getLeavePeriod(LocalDate startedAt) {
    Period period = Period.between(startedAt, LocalDate.now());
    var years = period.getYears();
    switch (years) {
      case 0:
        return 5;
      case 1:
      case 2:
      case 3:
      case 4:
        return 15;
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
        return 18;
      default:
        return 24;
    }
  }
}
