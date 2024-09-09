package com.example.NST.service;

import com.example.NST.converter.impl.SubjectConverter;
import com.example.NST.dto.DepartmentDTO;
import com.example.NST.dto.SubjectDTO;
import com.example.NST.model.Department;
import com.example.NST.model.Subject;
import com.example.NST.repository.DepartmentRepository;
import com.example.NST.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {
    @Autowired
    private SubjectConverter subjectConverter;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public SubjectDTO save(SubjectDTO subjectDto) throws Exception {
        Subject subjectToSave = subjectConverter.toEntity(subjectDto);

        if(subjectToSave.getDepartment() == null){
            throw new Exception("The subject you are trying to save" +
                    "must contain a department.");
        }

        if(subjectToSave.getDepartment().getId() == null){
            departmentRepository.save(subjectToSave.getDepartment());
        } else {
            Optional<Department> departmentOptFromDB =
                    departmentRepository.findById(subjectToSave
                            .getDepartment().getId());

            if(departmentOptFromDB.isEmpty()) {
                departmentRepository.save(subjectToSave.getDepartment());
            }
        }

        Subject savedSubject = subjectRepository.save(subjectToSave);

        return subjectConverter.toDto(savedSubject);
    }

    public List<SubjectDTO> getAll(Pageable pageable) {
        List<Subject> subjects = subjectRepository.findAll(pageable).getContent();
        List<SubjectDTO> subjectDTOs = new ArrayList<>();

        for(Subject s: subjects) {
            var subjectDTO = subjectConverter.toDto(s);
            subjectDTOs.add(subjectDTO);
        }

        return subjectDTOs;
    }

    public void delete(Long id) throws Exception {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            Subject subj = subject.get();
            subjectRepository.delete(subj);
        } else {
            throw new Exception("Subject does not exist!");
        }

    }

    public SubjectDTO findById(Long id) throws Exception {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            //postoji
            Subject subj = subject.get();
            return subjectConverter.toDto(subj);
        } else {
            throw new Exception("Subject does not exist!");
        }
    }

}
