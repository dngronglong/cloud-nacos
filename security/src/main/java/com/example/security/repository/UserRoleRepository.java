package com.example.security.repository;

import com.example.security.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    List<UserRole> findUserRolesByUserId(Long id);
}
