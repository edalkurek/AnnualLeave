package com.example.annualleavemodule.controller;

import com.example.annualleavemodule.bean.EmployeeCreateRequest;
import com.example.annualleavemodule.bean.EmployeeCreateResponse;
import com.example.annualleavemodule.exception.EmployeeCreatedBeforeException;
import com.example.annualleavemodule.service.IEmployeeService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Api(value="User Rest Service")
public class EmployeeController {

  private final IEmployeeService iEmployeeService;

  public EmployeeController(IEmployeeService iEmployeeService) {
    this.iEmployeeService = iEmployeeService;
  }

  @PostMapping("/create")
  public EmployeeCreateResponse createEmployee(@RequestBody EmployeeCreateRequest employeeCreateRequest)
      throws EmployeeCreatedBeforeException {
    return iEmployeeService.createEmployee(employeeCreateRequest);
  }
}
