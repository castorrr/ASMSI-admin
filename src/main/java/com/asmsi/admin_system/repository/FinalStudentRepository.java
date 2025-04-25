package com.asmsi.admin_system.repository;

import com.asmsi.admin_system.entity.FinalStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalStudentRepository extends JpaRepository<FinalStudent, Long> {
}

