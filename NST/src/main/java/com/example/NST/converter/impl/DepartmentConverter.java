package com.example.NST.converter.impl;

import com.example.NST.converter.DTOEntityConverter;
import com.example.NST.dto.DepartmentDTO;
import com.example.NST.model.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConverter
        implements DTOEntityConverter<DepartmentDTO, Department> {
    @Override
    public DepartmentDTO toDto(Department department) {
        return new DepartmentDTO(
                department.getId(),
                department.getName(),
                department.getShortName());
    }

    @Override
    public Department toEntity(DepartmentDTO departmentDTO) {
        return new Department(
                departmentDTO.getId(),
                departmentDTO.getName(),
                departmentDTO.getShortName());
    }
}
