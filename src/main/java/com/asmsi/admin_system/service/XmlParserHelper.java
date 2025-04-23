package com.asmsi.admin_system.service;

import com.asmsi.admin_system.entity.AdmissionData;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class XmlParserHelper {

    public static List<AdmissionData> parse(MultipartFile file, String schoolYear) throws Exception {
        List<AdmissionData> dataList = new ArrayList<>();

        InputStream inputStream = file.getInputStream();
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
        doc.getDocumentElement().normalize();

        NodeList rows = doc.getElementsByTagName("row");

        for (int i = 0; i < rows.getLength(); i++) {
            Node node = rows.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element row = (Element) node;

                AdmissionData data = new AdmissionData();
                data.setSchoolYear(schoolYear);
                data.setIdNum(getValue(row, "idNum"));
                data.setLastName(getValue(row, "lastName"));
                data.setFirstName(getValue(row, "firstName"));
                data.setMiddleName(getValue(row, "middleName"));
                data.setSuffix(getValue(row, "suffix"));
                data.setBrgy(getValue(row, "brgy"));
                data.setMunicipality(getValue(row, "municipality"));
                data.setProvince(getValue(row, "province"));
                data.setBirthDate(parseDate(getValue(row, "birthDate"))); // Using LocalDate
                data.setBirthPlace(getValue(row, "birthPlace"));
                data.setAge(parseInt(getValue(row, "age")));
                data.setExamPlace(getValue(row, "examPlace"));
                data.setIdNumber(getValue(row, "idNumber"));
                data.setLrn(getValue(row, "lrn"));
                data.setReligion(getValue(row, "religion"));
                data.setGender(getValue(row, "gender"));
                data.setCitizenship(getValue(row, "citizenship"));
                data.setElemSchool(getValue(row, "elemSchool"));
                data.setSchoolAddress(getValue(row, "schoolAddress"));
                data.setFatherName(getValue(row, "fatherName"));
                data.setFatherOccupation(getValue(row, "fatherOccupation"));
                data.setFatherContact(getValue(row, "fatherContact"));
                data.setMotherName(getValue(row, "motherName"));
                data.setMotherOccupation(getValue(row, "motherOccupation"));
                data.setMotherContact(getValue(row, "motherContact"));
                data.setGuardianName(getValue(row, "guardianName"));
                data.setGuardianRelationship(getValue(row, "guardianRelationship"));
                data.setGuardianOccupation(getValue(row, "guardianOccupation"));
                data.setGuardianContact(getValue(row, "guardianContact"));

                dataList.add(data);
            }
        }

        return dataList;
    }

    private static String getValue(Element element, String tag) {
        NodeList nodeList = element.getElementsByTagName(tag);
        if (nodeList != null && nodeList.getLength() > 0 && nodeList.item(0).hasChildNodes()) {
            return nodeList.item(0).getTextContent().trim();
        }
        return "";
    }

    private static LocalDate parseDate(String dateString) {
        try {
            // Adjust the date format as needed, for example, "yyyy-MM-dd"
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            return null; // Handle parsing errors by returning null
        }
    }

    private static int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0; // Return default value if parsing fails
        }
    }
}
