package com.asmsi.admin_system.repository;

import com.asmsi.admin_system.entity.FamilySaint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FamilySaintRepository extends JpaRepository<FamilySaint, Long> {
    List<FamilySaint> findBySchoolYear(String schoolYear);
}

