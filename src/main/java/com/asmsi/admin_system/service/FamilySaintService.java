package com.asmsi.admin_system.service;

import com.asmsi.admin_system.entity.FamilySaint;
import com.asmsi.admin_system.repository.FamilySaintRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilySaintService {

    private final FamilySaintRepository familySaintRepository;

    public FamilySaintService(FamilySaintRepository familySaintRepository) {
        this.familySaintRepository = familySaintRepository;
    }

    public List<FamilySaint> getBySchoolYear(String schoolYear) {
        return familySaintRepository.findBySchoolYear(schoolYear);
    }

    public FamilySaint save(FamilySaint familySaint) {
        return familySaintRepository.save(familySaint);
    }

    public List<FamilySaint> getAll() {
        return familySaintRepository.findAll();
    }
    
} 
