package com.example.NST.converter.impl;

import com.example.NST.converter.DTOEntityConverter;
import com.example.NST.dto.AcademicTitleMemberDTO;
import com.example.NST.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.NST.model.enumeration.MemberRole.REGULAR;

@Component
public class AcademicTitleMemberDTOConverter implements
        DTOEntityConverter<AcademicTitleMemberDTO, Member> {

    @Autowired
    private AcademicTitleConverter academicTitleConverter;
    @Autowired
    private ScientificFieldConverter scientificFieldConverter;

    @Override
    public AcademicTitleMemberDTO toDto(Member e) {
        return (e == null ? null : new AcademicTitleMemberDTO(
                e.getId(),
                e.getStartDate(),
                academicTitleConverter.toDto(e.getAcademicTitle()),
                scientificFieldConverter.toDto(e.getScientificField())
        ));
    }

    @Override
    public Member toEntity(AcademicTitleMemberDTO t) {
        if(t == null) return null;

        return Member.builder()
                .id(t.getId())
                .role(REGULAR)
                .startDate(t.getStartDate())
                .academicTitle(academicTitleConverter
                        .toEntity(t.getAcademicTitleDTO()))
                .scientificField(scientificFieldConverter
                        .toEntity(t.getScientificFieldDTO()))
                .build();
    }
}
