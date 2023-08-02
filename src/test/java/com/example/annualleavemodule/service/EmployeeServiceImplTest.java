package com.example.annualleavemodule.service;

import com.example.annualleavemodule.bean.EmployeeCreateRequest;
import com.example.annualleavemodule.bean.EmployeeCreateResponse;
import com.example.annualleavemodule.entity.AnnualLeave;
import com.example.annualleavemodule.entity.Employee;
import com.example.annualleavemodule.enums.UserType;
import com.example.annualleavemodule.exception.EmployeeCreatedBeforeException;
import com.example.annualleavemodule.repository.IEmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeeServiceImplTest {

    @Autowired
    private IEmployeeService employeeService;

    @MockBean
    private IEmployeeRepository employeeRepository;

    @Test
    public void testCreateEmployee() throws EmployeeCreatedBeforeException {

        Employee employee = new Employee("Eren", "Dalkurek", "edalkurek@gmail.com", "password",
                LocalDate.of(2022, 1, 1), new AnnualLeave(15), UserType.ROLE_USER);

        EmployeeCreateResponse response = employeeService.createEmployee(getRequest());

        Assertions.assertEquals(employee.getId(), response.getEmployeeId());
    }

    @Test
    public void testCreateEmployeeWithExistingEmail() {
        when(employeeRepository.findEmployeeByEmail(anyString()))
                .thenReturn(Optional.of(new Employee()));

        assertThrows(EmployeeCreatedBeforeException.class, () -> {
            employeeService.createEmployee(getRequest());
        });
    }


    private EmployeeCreateRequest getRequest(){
        EmployeeCreateRequest request = new EmployeeCreateRequest();
        request.setName("Eren");
        request.setSurname("Dalkurek");
        request.setEmail("edalkurek@gmail.com");
        request.setPassword("password");
        request.setStartedAt(LocalDate.of(2022, 1, 1));
        request.setUserType(UserType.ROLE_USER);
        return request;
    }


}
