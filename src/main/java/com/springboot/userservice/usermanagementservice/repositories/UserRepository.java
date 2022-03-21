package com.springboot.userservice.usermanagementservice.repositories;

import com.springboot.userservice.usermanagementservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
