package com.example.NST.converter.impl;

import com.example.NST.converter.DTOEntityConverter;
import com.example.NST.dto.ScientificFieldDTO;
import com.example.NST.model.ScientificField;
import org.springframework.stereotype.Component;

@Component
public class ScientificFieldConverter implements DTOEntityConverter
        <ScientificFieldDTO, ScientificField> {

    @Override
    public ScientificFieldDTO toDto(ScientificField e) {
        if(e == null) {
            return null;
        }

        return new ScientificFieldDTO(e.getId(), e.getFieldName());
    }

    @Override
    public ScientificField toEntity(ScientificFieldDTO t) {
        if(t == null) {
            return null;
        }

        return new ScientificField(t.getId(), t.getFieldName());
    }
}
