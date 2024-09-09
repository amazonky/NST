package com.example.NST.service;

import com.example.NST.converter.impl.ScientificFieldConverter;
import com.example.NST.dto.ScientificFieldDTO;
import com.example.NST.dto.SubjectDTO;
import com.example.NST.model.ScientificField;
import com.example.NST.model.Subject;
import com.example.NST.repository.ScientificFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScientificFieldService  {
    @Autowired
    private ScientificFieldRepository scientificFieldRepository;
    @Autowired
    private ScientificFieldConverter scientificFieldConverter;

    public ScientificFieldDTO save(ScientificFieldDTO scientificFieldDTO) throws Exception {
        ScientificField fieldToSave =
                scientificFieldConverter.toEntity(scientificFieldDTO);
        fieldToSave.setId(null);
        ScientificField fieldSaved = scientificFieldRepository
                .save(fieldToSave);
        return scientificFieldConverter.toDto(fieldSaved);
    }

    public List<ScientificFieldDTO> getAll(Pageable pageable) {
        List<ScientificField> scientificFields = scientificFieldRepository.findAll(pageable).getContent();
        List<ScientificFieldDTO> scientificFieldDTOs = new ArrayList<>();

        for(var sf: scientificFields) {
            var scientificFieldDTO = scientificFieldConverter.toDto(sf);
            scientificFieldDTOs.add(scientificFieldDTO);
        }

        return scientificFieldDTOs;
    }

    public void delete(Long id) throws Exception {
        if(id == null){
            throw new Exception("Id must not be null.");
        }

        var scientificFieldOptFromDB = scientificFieldRepository.findById(id);

        if(scientificFieldOptFromDB.isEmpty()){
            throw new Exception("There is no scientific field with said id.");
        }

        scientificFieldRepository.deleteById(id);
    }

}
