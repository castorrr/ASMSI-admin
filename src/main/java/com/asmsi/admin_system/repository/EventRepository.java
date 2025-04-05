package com.asmsi.admin_system.repository;

import com.asmsi.admin_system.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE YEAR(e.dateTime) = :year")
    List<Event> findByYear(@Param("year") int year);
}
