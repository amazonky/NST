package com.example.NST.converter.impl;

import com.example.NST.converter.DTOEntityConverter;
import com.example.NST.dto.EducationTitleDTO;
import com.example.NST.model.EducationTitle;
import org.springframework.stereotype.Component;

@Component
public class EducationTitleConverter implements DTOEntityConverter
        <EducationTitleDTO, EducationTitle> {
    @Override
    public EducationTitleDTO toDto(EducationTitle e) {
        if(e == null) return null;

        return new EducationTitleDTO(e.getId(), e.getTitleName());
    }

    @Override
    public EducationTitle toEntity(EducationTitleDTO t) {
        if(t == null) return null;

        return new EducationTitle(t.getId(), t.getTitleName());
    }
}
