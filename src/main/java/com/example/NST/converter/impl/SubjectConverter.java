package com.example.NST.converter.impl;

import com.example.NST.converter.DTOEntityConverter;
import com.example.NST.dto.SubjectDTO;
import com.example.NST.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectConverter
        implements DTOEntityConverter<SubjectDTO, Subject> {

    @Autowired
    private DepartmentConverter departmentConverter;

    @Override
    public SubjectDTO toDto(Subject subject) {
        if(subject == null) {
            return null;
        }

        return new SubjectDTO(
                subject.getId(),
                subject.getName(),
                subject.getEspb(),
                departmentConverter.toDto(
                        subject.getDepartment()));
    }

    @Override
    public Subject toEntity(SubjectDTO subjectDTO) {
        if(subjectDTO == null) {
            return null;
        }

        return new Subject(
                subjectDTO.getId(),
                subjectDTO.getName(),
                subjectDTO.getEspb(),
                departmentConverter
                        .toEntity(subjectDTO.getDepartmentDTO()));
    }
}
