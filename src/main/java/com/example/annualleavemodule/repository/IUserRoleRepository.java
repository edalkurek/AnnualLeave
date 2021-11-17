package com.example.annualleavemodule.repository;

import com.example.annualleavemodule.entity.UserRole;
import com.example.annualleavemodule.enums.UserType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {

  @Query("select ur from UserRole ur where ur.userType = :userType")
  Optional<UserRole> getUserRoleByName(UserType userType);
}
