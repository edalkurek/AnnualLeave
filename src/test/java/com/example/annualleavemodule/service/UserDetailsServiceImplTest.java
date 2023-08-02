package com.example.annualleavemodule.service;

import com.example.annualleavemodule.entity.Employee;
import com.example.annualleavemodule.repository.IEmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserDetailsServiceImplTest {

    @Mock
    private IEmployeeRepository iEmployeeRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoadUserByUsername_UserExists() {

        String email = "edalkurek@gmail.com";
        Employee employee = new Employee();
        employee.setEmail(email);


        when(iEmployeeRepository.findEmployeeByEmail(email)).thenReturn(Optional.of(employee));


        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email);


        assertNotNull(userDetails);
        assertEquals(email, userDetails.getUsername());

    }

    @Test
    public void testLoadUserByUsername_UserNotExists() {

        String email = "edalkurek@gmail.com";

        when(iEmployeeRepository.findEmployeeByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsServiceImpl.loadUserByUsername(email);
        });
    }
}
