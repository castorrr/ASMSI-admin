package com.asmsi.admin_system.repository;

import com.asmsi.admin_system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByIdNumber(String idNumber); // Customize as needed
}