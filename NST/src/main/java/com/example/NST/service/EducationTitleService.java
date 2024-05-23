package com.example.NST.service;

import com.example.NST.converter.impl.EducationTitleConverter;
import com.example.NST.dto.EducationTitleDTO;
import com.example.NST.model.EducationTitle;
import com.example.NST.repository.EducationTitleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EducationTitleService {
    @Autowired
    private EducationTitleRepository educationTitleRepository;
    @Autowired
    private EducationTitleConverter educationTitleConverter;

    public EducationTitleDTO save(@Valid EducationTitleDTO educationTitleDTO)
            throws Exception {
        EducationTitle titleToSave =
            educationTitleConverter.toEntity(educationTitleDTO);
        titleToSave.setId(null);
        EducationTitle titleSaved =
                educationTitleRepository.save(titleToSave);

        return educationTitleConverter.toDto(titleSaved);
    }

    public List<EducationTitleDTO> getAll(Pageable pageable) {
        List<EducationTitle> educationTitles = educationTitleRepository.findAll(pageable).getContent();
        List<EducationTitleDTO> educationTitleDTOS = new ArrayList<>();

        for(var et: educationTitles) {
            var educationTitle = educationTitleConverter.toDto(et);
            educationTitleDTOS.add(educationTitle);
        }

        return educationTitleDTOS;
    }

    public void delete(Long id) throws Exception {
        if(id == null){
            throw new Exception("ID must not be null.");
        }

        var educationTitleOptFromDB = educationTitleRepository.findById(id);

        if(educationTitleOptFromDB.isEmpty()){
            throw new Exception("There is no scientific field with said ID.");
        }

        educationTitleRepository.deleteById(id);
    }

}
