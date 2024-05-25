package com.example.NST.converter.impl;

import com.example.NST.converter.DTOEntityConverter;
import com.example.NST.dto.MemberHistoryDTO;
import com.example.NST.model.MemberHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberHistoryConverter implements DTOEntityConverter
        <MemberHistoryDTO, MemberHistory> {
    @Autowired
    private DepartmentConverter departmentConverter;
    @Autowired
    private MemberConverter memberConverter;

    @Override
    public MemberHistoryDTO toDto(MemberHistory e) {
        if(e == null) return null;

        return new MemberHistoryDTO(
                e.getId(),
                e.getStartDate(),
                e.getEndDate(),
                e.getRole(),
                departmentConverter
                        .toDto(e.getDepartment()),
                memberConverter
                        .toDto(e.getMember()));
    }

    @Override
    public MemberHistory toEntity(MemberHistoryDTO t) {
        if(t == null) return null;

        return new MemberHistory(
                t.getId(),
                t.getStartDate(),
                t.getEndDate(),
                t.getRole(),
                departmentConverter
                        .toEntity(t.getDepartmentDTO()),
                memberConverter
                        .toEntity(t.getMemberDTO()));
    }
}
