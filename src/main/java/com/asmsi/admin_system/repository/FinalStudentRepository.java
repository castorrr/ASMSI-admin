package com.asmsi.admin_system.repository;

import com.asmsi.admin_system.entity.FinalStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface FinalStudentRepository extends JpaRepository<FinalStudent, Long> {

    List<FinalStudent> findByFamilySaint(String familySaint);

    // Custom query to find students by School Year
    List<FinalStudent> findBySchoolYear(String schoolYear);

    // Custom query to get distinct Family Saints for the filter dropdown
    @Query("SELECT DISTINCT f.familySaint FROM FinalStudent f")
    List<String> findDistinctFamilySaints();

    // Custom query to get distinct School Years for the filter dropdown
    @Query("SELECT DISTINCT f.schoolYear FROM FinalStudent f")
    List<String> findDistinctSchoolYears();

    List<FinalStudent> findByFamilySaintAndSchoolYear(String familySaint, String schoolYear);

}

