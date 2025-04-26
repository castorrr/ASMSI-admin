package com.asmsi.admin_system.repository;

import com.asmsi.admin_system.entity.EventAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventAttendanceRepository extends JpaRepository<EventAttendance, Long> {
    List<EventAttendance> findByEventId(String eventId);
}
