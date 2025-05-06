package com.asmsi.admin_system.repository;

import com.asmsi.admin_system.entity.FamilySaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FamilySaintRepository extends JpaRepository<FamilySaint, Long> {
    List<FamilySaint> findBySchoolYear(String schoolYear);

    
    @Query(value = """
        SELECT 
            s.saint_name,
            m.mother_sister,
            b.building_name
        FROM LATERAL (
            SELECT id, setting_id, saint_name,
                   ROW_NUMBER() OVER (PARTITION BY setting_id ORDER BY id) AS rn
            FROM admission_saints
        ) s
        JOIN LATERAL (
            SELECT setting_id, mother_sister,
                   ROW_NUMBER() OVER (PARTITION BY setting_id ORDER BY id) AS rn
            FROM admission_mother_sisters
        ) m ON m.setting_id = s.setting_id AND m.rn = s.rn
        JOIN LATERAL (
            SELECT setting_id, building_name,
                   ROW_NUMBER() OVER (PARTITION BY setting_id ORDER BY id) AS rn
            FROM admission_buildings
        ) b ON b.setting_id = s.setting_id AND b.rn = s.rn
        JOIN admission_setting a ON a.id = s.setting_id
        WHERE a.school_year = :schoolYear
        ORDER BY s.setting_id, s.rn
    """, nativeQuery = true)
    List<Object[]> findSaintsWithDetails(@Param("schoolYear") String schoolYear);
    
    
}


