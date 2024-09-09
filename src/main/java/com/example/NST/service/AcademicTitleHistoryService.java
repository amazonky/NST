package com.example.NST.service;

import com.example.NST.converter.impl.AcademicTitleHistoryConverter;
import com.example.NST.dto.AcademicTitleHistoryDTO;
import com.example.NST.model.AcademicTitleHistory;
import com.example.NST.repository.AcademicTitleHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcademicTitleHistoryService {
    @Autowired
    private AcademicTitleHistoryRepository academicTitleHistoryRepository;
    @Autowired
    private AcademicTitleHistoryConverter
            academicTitleHistoryConverter;

    private List<AcademicTitleHistoryDTO> listOfHistoriesToListOfDTOs(
            List<AcademicTitleHistory> histories) {

        List<AcademicTitleHistoryDTO> historyDTOs = new ArrayList<>();

        for(var h : histories) {
            AcademicTitleHistoryDTO historyDTO = academicTitleHistoryConverter.toDto(h);
            historyDTOs.add(historyDTO);
        }

        return historyDTOs;
    }

    public List<AcademicTitleHistoryDTO> getAll(Pageable pageable) {
        return listOfHistoriesToListOfDTOs(
            academicTitleHistoryRepository.findAll(pageable).getContent());
    }


    public List<AcademicTitleHistoryDTO> historiesForMember(Long memberId){
        return listOfHistoriesToListOfDTOs(academicTitleHistoryRepository
                .findByMemberId(memberId));

    }
}
