package com.asmsi.admin_system.repository;

import com.asmsi.admin_system.entity.AdmissionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionDataRepository extends JpaRepository<AdmissionData, Long> {

    // Optional: find by idNum (or any other field if you need custom queries later)
    List<AdmissionData> findByIdNum(String idNum);

    // Optional: find by school year if you want to filter
    List<AdmissionData> findBySchoolYear(String schoolYear);
    // Search by first name
    List<AdmissionData> findAllByFirstNameIgnoreCase(String firstName);

    // Search by last name
    List<AdmissionData> findAllByLastNameIgnoreCase(String lastName);

    // Search by both first name and last name
    List<AdmissionData> findAllByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

}
