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

    // ✅ Save a single admission record (used in form submission)
    public void save(AdmissionData admissionData) {
        admissionDataRepository.save(admissionData);
    }

    

    // ✅ Save a list of admission records (used in XML upload)
    public void saveAll(List<AdmissionData> dataList) {
        admissionDataRepository.saveAll(dataList);
    }

    // ✅ Fetch admission data by ID number (used in form prefill)
    public List<AdmissionData> searchStudents(String idNum) {
        if (idNum != null && !idNum.isEmpty()) {
            return admissionDataRepository.findByIdNum(idNum);
        } else {
            return admissionDataRepository.findAll();
        }
    }

    // ✅ Check duplicate code number for saint/year
    public boolean existsByFamilySaintAndCodeNumberAndSchoolYear(String familySaint, int codeNumber, String schoolYear) {
        return admissionDataRepository.existsByFamilySaintAndCodeNumberAndSchoolYear(familySaint, codeNumber, schoolYear);
    }

    // ✅ Fetch all records
    public List<AdmissionData> getAllAdmissions() {
        return admissionDataRepository.findAll();
    }

    // ✅ For Reports page
    public List<AdmissionData> getByFamilySaintAndSchoolYear(String familySaint, String schoolYear) {
        return admissionDataRepository.findByFamilySaintAndSchoolYear(familySaint, schoolYear);
    }

    public List<AdmissionData> getByFamilySaint(String familySaint) {
        return admissionDataRepository.findByFamilySaint(familySaint);
    }

    public List<AdmissionData> getBySchoolYear(String schoolYear) {
        return admissionDataRepository.findBySchoolYear(schoolYear);
    }

    public List<String> getDistinctFamilySaints() {
        return admissionDataRepository.findDistinctFamilySaints();
    }

    public List<String> getDistinctSchoolYears() {
        return admissionDataRepository.findDistinctSchoolYears();
    }
}
