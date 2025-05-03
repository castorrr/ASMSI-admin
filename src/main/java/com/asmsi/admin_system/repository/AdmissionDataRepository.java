package com.asmsi.admin_system.repository;

import com.asmsi.admin_system.entity.AdmissionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionDataRepository extends JpaRepository<AdmissionData, Long> {

    // Optional: find by idNum (or any other field if you need custom queries later)
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


   
    




}
