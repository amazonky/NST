package com.example.NST.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentChangeMemberDTO {
    private Long id;
    private Long newDepartmentId;
}
