package com.example.NST.converter.impl;

import com.example.NST.converter.DTOEntityConverter;
import com.example.NST.dto.AcademicTitleHistoryDTO;
import com.example.NST.model.AcademicTitleHistory;
import com.example.NST.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.NST.model.enumeration.MemberRole.REGULAR;

@Component
public class AcademicTitleHistoryConverter implements DTOEntityConverter
        <AcademicTitleHistoryDTO,
                                                                AcademicTitleHistory> {
    @Autowired
    private AcademicTitleConverter academicTitleConverter;
    @Autowired
    private ScientificFieldConverter scientificFieldConverter;

    @Override
    public AcademicTitleHistoryDTO toDto(AcademicTitleHistory e) {
        if (e == null) return null;

        return new AcademicTitleHistoryDTO(
            e.getId(), e.getStartDate(), e.getEndDate(),
                e.getMember() == null ? null : e.getMember().getId(),
                academicTitleConverter.toDto(e.getAcademicTitle()),
                scientificFieldConverter.toDto(e.getScientificField()));
    }

    @Override
    public AcademicTitleHistory toEntity(AcademicTitleHistoryDTO t) {
        if (t == null) return null;

        return new AcademicTitleHistory(
                t.getId(), t.getStartDate(), t.getEndDate(),
                Member.builder()
                        .id(t.getMemberId())
                        .role(REGULAR)
                        .firstName("DUMMY")
                        .lastName("DUMMY")
                        .build(),
                academicTitleConverter.toEntity(t.getAcademicTitleDTO()),
                scientificFieldConverter.toEntity(t.getScientificFieldDTO())
        );
    }
}
