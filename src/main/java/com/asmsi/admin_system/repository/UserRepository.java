package com.asmsi.admin_system.repository;

import com.asmsi.admin_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findByIsApprovedFalse();
    List<User> findByIsApprovedTrue();
}
