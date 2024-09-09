package com.example.NST.converter.impl;

import com.example.NST.converter.DTOEntityConverter;
import com.example.NST.dto.DepartmentChangeMemberDTO;
import com.example.NST.model.Department;
import com.example.NST.model.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DepartmentChangeMemberDTOConverter implements
        DTOEntityConverter<DepartmentChangeMemberDTO, Member> {

    @Override
    public DepartmentChangeMemberDTO toDto(Member e) {
        if(e == null) return null;

        return new DepartmentChangeMemberDTO(
                e.getId(), (e.getDepartment() == null ? null : e.getDepartment().getId()));
    }

    @Override
    public Member toEntity(DepartmentChangeMemberDTO t) {
        if(t == null) return null;

        return Member.builder()
                .id(t.getId())
                .firstName("DUMMY")
                .lastName("DUMMY")
                .department(Department.builder()
                        .id(t.getNewDepartmentId())
                        .build())
                .build();
    }

}
