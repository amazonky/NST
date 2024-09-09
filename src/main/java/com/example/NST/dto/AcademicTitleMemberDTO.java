package com.example.NST.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AcademicTitleMemberDTO {
    private Long id;
    private LocalDate startDate;
    private AcademicTitleDTO academicTitleDTO;
    private ScientificFieldDTO scientificFieldDTO;
}
