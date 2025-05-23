package com.asmsi.admin_system.repository;

import com.asmsi.admin_system.entity.AdmissionSaint;
import com.asmsi.admin_system.entity.AdmissionSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface AdmissionSettingRepository extends JpaRepository<AdmissionSetting, Long> {
    boolean existsBySchoolYear(String schoolYear);

    @Query("SELECT DISTINCT a.schoolYear FROM AdmissionSetting a ORDER BY a.schoolYear DESC")
List<String> findAllSchoolYears();




@Query("SELECT s FROM AdmissionSaint s WHERE s.setting.schoolYear = :schoolYear")
List<AdmissionSaint> findSaintsBySchoolYear(@Param("schoolYear") String schoolYear);

@Query("SELECT a FROM AdmissionSetting a WHERE a.schoolYear = :schoolYear")
AdmissionSetting findBySchoolYear(String schoolYear);

@Modifying
@Query(value = "DELETE FROM admission_saints WHERE setting_id = :settingId", nativeQuery = true)
void deleteSaintsBySettingId(@Param("settingId") Long settingId);

@Modifying
@Query(value = "DELETE FROM admission_mother_sisters WHERE setting_id = :settingId", nativeQuery = true)
void deleteMotherSistersBySettingId(@Param("settingId") Long settingId);

@Modifying
@Query(value = "DELETE FROM admission_buildings WHERE setting_id = :settingId", nativeQuery = true)
void deleteBuildingsBySettingId(@Param("settingId") Long settingId);



}

