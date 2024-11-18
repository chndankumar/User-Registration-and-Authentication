package com.edtech.userRegistration.Repository;

import com.edtech.userRegistration.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRegistrationRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}
