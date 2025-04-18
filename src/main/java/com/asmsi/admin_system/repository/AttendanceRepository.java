package com.asmsi.admin_system.repository;

import com.asmsi.admin_system.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
