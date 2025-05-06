package com.asmsi.admin_system.repository;

import com.asmsi.admin_system.entity.AdmissionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionDataRepository extends JpaRepository<AdmissionData, Long> {

    List<AdmissionData> findAllByIdNum(String idNum);

    @Query("SELECT a.schoolYear, COUNT(a) FROM AdmissionData a GROUP BY a.schoolYear")
    List<Object[]> countBySchoolYear();

    List<AdmissionData> findByIdNum(String idNum);

    boolean existsByFamilySaintAndCodeNumberAndSchoolYear(String familySaint, int codeNumber, String schoolYear);

    List<AdmissionData> findByFamilySaintAndSchoolYear(String familySaint, String schoolYear);

    List<AdmissionData> findByFamilySaint(String familySaint);

    List<AdmissionData> findBySchoolYear(String schoolYear);

    @Query("SELECT DISTINCT a.familySaint FROM AdmissionData a")
    List<String> findDistinctFamilySaints();

    @Query("SELECT DISTINCT a.schoolYear FROM AdmissionData a")
    List<String> findDistinctSchoolYears();

    @Query("SELECT a.examPlace, COUNT(a), SUM(CASE WHEN a.status = 'Arrived' THEN 1 ELSE 0 END) " +
           "FROM AdmissionData a GROUP BY a.examPlace")
    List<Object[]> getExamPlaceStats();

    // ✅ Use this for fast count of Arrived
    long countByStatusIgnoreCase(String status);

    // ✅ PostgreSQL-compatible version
    @Query(value = """
        SELECT family_saint, COUNT(*) AS total, STRING_AGG(code_number::text, ',' ORDER BY code_number)
        FROM admission_data
        WHERE family_saint IS NOT NULL
        GROUP BY family_saint
    """, nativeQuery = true)
    List<Object[]> getFamilySaintStatsSummary();

    List<AdmissionData> findAll(); // Optional if already inherited

    
}
