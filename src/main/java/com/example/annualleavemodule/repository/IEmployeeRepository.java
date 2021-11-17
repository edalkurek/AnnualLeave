package com.example.annualleavemodule.repository;

import com.example.annualleavemodule.entity.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

  @Query("select e from Employee e where e.email = :email")
  Optional<Employee> findEmployeeByEmail(String email);
}
