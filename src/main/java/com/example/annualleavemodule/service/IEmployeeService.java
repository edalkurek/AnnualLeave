package com.example.annualleavemodule.service;

import com.example.annualleavemodule.bean.EmployeeCreateRequest;
import com.example.annualleavemodule.bean.EmployeeCreateResponse;
import com.example.annualleavemodule.exception.EmployeeCreatedBeforeException;

public interface IEmployeeService {

  EmployeeCreateResponse createEmployee(EmployeeCreateRequest employeeCreateRequest)
      throws EmployeeCreatedBeforeException;
}
