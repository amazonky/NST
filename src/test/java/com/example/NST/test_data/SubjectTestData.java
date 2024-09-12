package com.example.NST.test_data;

import com.example.NST.dto.DepartmentDTO;
import com.example.NST.dto.SubjectDTO;
import com.example.NST.model.Department;
import com.example.NST.model.Subject;

public interface SubjectTestData {
    Department WELL_FORMED_DEPARTMENT_WITH_ID = new Department(
            1L, "exampleName", "exampleShortName");
    Subject WELL_FORMED_SUBJECT_WITH_ID = new Subject(
            1L, "exampleName",
            4, WELL_FORMED_DEPARTMENT_WITH_ID);
    DepartmentDTO WELL_FORMED_DEPARTMENT_DTO_WITH_ID = new DepartmentDTO(
            1L, "exampleName", "exampleShortName");
    SubjectDTO WELL_FORMED_SUBJECT_DTO_WITH_ID = new SubjectDTO(
            1L, "exampleName", 4,
            WELL_FORMED_DEPARTMENT_DTO_WITH_ID);
    SubjectDTO NULL_DEPARTMENT_SUBJECT_DTO_WITH_ID = new SubjectDTO(
            1L, "exampleName", 4, null);
    Subject NULL_DEPARTMENT_SUBJECT_WITH_ID = new Subject(
            1L, "exampleName", 4, null);

    DepartmentDTO NULL_ID_DEPARTMENT_DTO = new DepartmentDTO(
            null, "exampleName", "exampleShortName");
    SubjectDTO NULL_ID_DEPARTMENT_SUBJECT_DTO_WITH_ID = new SubjectDTO(
            1L, "exampleName", 4, NULL_ID_DEPARTMENT_DTO);

    Department NULL_ID_DEPARTMENT = new Department(
            null, "exampleName", "exampleShortName");
    Subject NULL_ID_DEPARTMENT_SUBJECT = new Subject(
            1L, "exampleName", 4, NULL_ID_DEPARTMENT);

    DepartmentDTO UNKNOWN_ID_DEPARTMENT_DTO = new DepartmentDTO(
            -1L, "exampleName", "exampleShortName");
    Department UNKNOWN_ID_DEPARTMENT = new Department(
            -1L, "exampleName", "exampleShortName");

    SubjectDTO UNKNOWN_ID_DEPARTMENT_SUBJECT_DTO_WITH_ID = new SubjectDTO(
            -1L, "exampleName", 4, NULL_ID_DEPARTMENT_DTO);
    Subject UNKNOWN_ID_DEPARTMENT_SUBJECT = new Subject(
            -1L, "exampleName", 4, NULL_ID_DEPARTMENT);

    SubjectDTO UNKNOWN_ID_SUBJECT_DTO = new SubjectDTO(
            -1L, "exampleName", 4,
            WELL_FORMED_DEPARTMENT_DTO_WITH_ID);
}
