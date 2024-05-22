package com.example.NST.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubjectDTO {
    private Long id;
    private String name;
    private int espb;
    private DepartmentDTO departmentDTO;
}
