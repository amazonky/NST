package com.example.NST.converter.impl;

import com.example.NST.converter.DTOEntityConverter;
import com.example.NST.dto.AcademicTitleDTO;
import com.example.NST.model.AcademicTitle;
import org.springframework.stereotype.Component;

@Component
public class AcademicTitleConverter implements DTOEntityConverter
        <AcademicTitleDTO, AcademicTitle> {
    @Override
    public AcademicTitleDTO toDto(AcademicTitle e) {
        if(e == null) return null;

        return  new AcademicTitleDTO(e.getId(), e.getTitleName());
    }

    @Override
    public AcademicTitle toEntity(AcademicTitleDTO t) {
        if(t == null) return null;

        return new AcademicTitle(t.getId(), t.getTitleName());
    }
}
