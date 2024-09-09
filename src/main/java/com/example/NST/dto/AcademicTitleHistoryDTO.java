package com.example.NST.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AcademicTitleHistoryDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long memberId;
    private AcademicTitleDTO academicTitleDTO;
    private ScientificFieldDTO scientificFieldDTO;
}
