package com.asmsi.admin_system.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository; // ✅ <--- This line is missing
import java.util.List;
import com.asmsi.admin_system.entity.AdmissionData;


@Repository
public interface StudentUploadRepository extends JpaRepository<AdmissionData, Long> {
    @Query("SELECT a.schoolYear, COUNT(a) FROM AdmissionData a GROUP BY a.schoolYear")
    List<Object[]> countUploadsBySchoolYear();
}
