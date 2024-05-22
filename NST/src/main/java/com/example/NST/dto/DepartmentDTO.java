package com.example.NST.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentDTO {
    private Long id;
    private String name;
    private String shortName;
}