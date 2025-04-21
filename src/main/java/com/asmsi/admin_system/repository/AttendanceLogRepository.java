package com.asmsi.admin_system.repository;

import com.asmsi.admin_system.entity.AttendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, Long> {
    List<AttendanceLog> findByIdNumber(String idNumber);
    Optional<AttendanceLog> findTopByIdNumberAndDateOrderByTimeInDesc(String idNumber, LocalDate date);

}
