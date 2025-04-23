package com.asmsi.admin_system.service;

import com.asmsi.admin_system.entity.AdmissionData;
import com.asmsi.admin_system.repository.AdmissionDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdmissionService {

    @Autowired
    private AdmissionDataRepository admissionDataRepository;

    // Save a list of admission records
    public void saveAll(List<AdmissionData> dataList) {
        admissionDataRepository.saveAll(dataList);
    }

    // Optional: Get all admission records
    public List<AdmissionData> getAllAdmissions() {
        return admissionDataRepository.findAll();
    }

    // Optional: Get admissions by school year
    public List<AdmissionData> getAdmissionsBySchoolYear(String schoolYear) {
        return admissionDataRepository.findBySchoolYear(schoolYear);
    }

    // Search students based on first name, last name, or both
    public List<AdmissionData> searchStudents(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return admissionDataRepository.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);
        } else if (firstName != null) {
            return admissionDataRepository.findAllByFirstNameIgnoreCase(firstName);
        } else if (lastName != null) {
            return admissionDataRepository.findAllByLastNameIgnoreCase(lastName);
        } else {
            return admissionDataRepository.findAll();  // Return all students if no search criteria
        }
    }
}
