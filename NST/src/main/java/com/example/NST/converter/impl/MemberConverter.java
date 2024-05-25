package com.example.NST.converter.impl;

import com.example.NST.converter.DTOEntityConverter;
import com.example.NST.dto.MemberDTO;
import com.example.NST.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter implements DTOEntityConverter<MemberDTO, Member> {
    @Autowired
    private AcademicTitleConverter academicTitleConverter;
    @Autowired
    private EducationTitleConverter educationTitleConverter;
    @Autowired
    private ScientificFieldConverter scientificFieldConverter;
    @Autowired
    private DepartmentConverter departmentConverter;

    @Override
    public MemberDTO toDto(Member e) {
        if (e == null) return null;

        return new MemberDTO(
                e.getId(), e.getFirstName(), e.getLastName(), e.getStartDate(),
                e.getRole(),
                departmentConverter.toDto(e.getDepartment()),
                academicTitleConverter.toDto(e.getAcademicTitle()),
                educationTitleConverter.toDto(e.getEducationTitle()),
                scientificFieldConverter.toDto(e.getScientificField()));
    }

    @Override
    public Member toEntity(MemberDTO t) {
        if(t == null) return null;

        return new Member(
                t.getId(),
                t.getFirstName(),
                t.getLastName(),
                t.getRole(),
                t.getStartDate(),
                departmentConverter.toEntity(t.getDepartmentDTO()),
                academicTitleConverter.toEntity(t.getAcademicTitleDTO()),
                educationTitleConverter.toEntity(t.getEducationTitleDTO()),
                scientificFieldConverter.toEntity(t.getScientificFieldDTO()));
    }
}
