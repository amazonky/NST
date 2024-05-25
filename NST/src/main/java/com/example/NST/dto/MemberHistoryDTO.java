package com.example.NST.dto;

import com.example.NST.model.enumeration.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class MemberHistoryDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private MemberRole role;
    private DepartmentDTO departmentDTO;
    private MemberDTO memberDTO;
}
