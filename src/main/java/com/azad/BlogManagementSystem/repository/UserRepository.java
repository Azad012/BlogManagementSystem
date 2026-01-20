package com.azad.BlogManagementSystem.repository;

import com.azad.BlogManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
