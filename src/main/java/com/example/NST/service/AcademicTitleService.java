package com.example.NST.service;

import com.example.NST.converter.impl.AcademicTitleConverter;
import com.example.NST.dto.AcademicTitleDTO;
import com.example.NST.model.AcademicTitle;
import com.example.NST.repository.AcademicTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AcademicTitleService {
    @Autowired
    private AcademicTitleRepository academicTitleRepository;
    @Autowired
    private AcademicTitleConverter academicTitleConverter;

    public AcademicTitleDTO save(AcademicTitleDTO academicTitleDTO) throws Exception {
        AcademicTitle titleForSaving = academicTitleConverter.toEntity(academicTitleDTO);
        titleForSaving.setId(null);
        AcademicTitle titleSaved = academicTitleRepository.save(titleForSaving);

        return academicTitleConverter.toDto(titleSaved);
    }

    public List<AcademicTitleDTO> getAll(Pageable pageable) {
        List<AcademicTitle> academicTitles = academicTitleRepository.findAll(pageable).getContent();
        List<AcademicTitleDTO> academicTitleDTOS = new ArrayList<>();

        for(var at: academicTitles) {
            AcademicTitleDTO academicTitleDTO = academicTitleConverter.toDto(at);
            academicTitleDTOS.add(academicTitleDTO);
        }

        return academicTitleDTOS;
    }

    public void delete(Long id) throws Exception {
        if(id == null){
            throw new Exception("You must input an id to delete.");
        }

        final Optional<AcademicTitle> academicTitleOptFromDB = academicTitleRepository.findById(id);

        if(academicTitleOptFromDB.isEmpty()){
            throw new Exception("There is no academic title with said id.");
        }

        academicTitleRepository.deleteById(id);
    }

}
