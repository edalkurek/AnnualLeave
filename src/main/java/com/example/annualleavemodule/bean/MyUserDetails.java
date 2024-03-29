package com.example.annualleavemodule.bean;

import com.example.annualleavemodule.entity.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.annualleavemodule.enums.UserType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

  private Employee employee;

  public MyUserDetails(Employee employee) {
    this.employee = employee;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    UserType userType = employee.getUserType();
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(userType.name()));
    return authorities;
  }

  @Override
  public String getPassword() {
    return employee.getPassword();
  }

  @Override
  public String getUsername() {
    return employee.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
