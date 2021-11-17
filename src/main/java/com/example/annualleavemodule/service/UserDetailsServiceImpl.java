package com.example.annualleavemodule.service;

import com.example.annualleavemodule.bean.MyUserDetails;
import com.example.annualleavemodule.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private IEmployeeRepository iEmployeeRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    var employee = iEmployeeRepository.findEmployeeByEmail(email).orElse(null);
    if (employee == null) {
      throw new UsernameNotFoundException("Could not find user");
    }

    return new MyUserDetails(employee);
  }
}
