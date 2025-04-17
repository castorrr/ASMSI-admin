package com.asmsi.admin_system.repository;

import com.asmsi.admin_system.entity.ViaDropdown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ViaDropdownRepository extends JpaRepository<ViaDropdown, Long> {

    @Query("SELECT DISTINCT v.via FROM ViaDropdown v WHERE v.via IS NOT NULL AND v.via <> ''")
    List<String> findAllUniqueVia();
}
