package com.asmsi.admin_system.service;

import com.asmsi.admin_system.repository.StudentUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UploadService {

    @Autowired
    private StudentUploadRepository studentUpload;

    public List<Map<String, Object>> getUploadSummary() {
        List<Object[]> rawData = studentUpload.countUploadsBySchoolYear();
        List<Map<String, Object>> summary = new ArrayList<>();

        int counter = 1;
        for (Object[] row : rawData) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", counter++);
            map.put("schoolYear", row[0]);
            map.put("totalStudents", row[1]);
            summary.add(map);
        }
        return summary;
    }
}
