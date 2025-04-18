package com.asmsi.admin_system.repository;

import com.asmsi.admin_system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByIdNumber(String idNumber);
}
