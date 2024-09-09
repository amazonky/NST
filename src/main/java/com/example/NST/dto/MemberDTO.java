package com.example.NST.dto;

import com.example.NST.model.enumeration.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class MemberDTO {
        private Long id;
        private String firstName;
        private String lastName;
        private LocalDate startDate;
        private MemberRole role;
        private DepartmentDTO departmentDTO;
        private AcademicTitleDTO academicTitleDTO;
        private EducationTitleDTO educationTitleDTO;
        private ScientificFieldDTO scientificFieldDTO;
}
