package com.example.NST.repository;

import com.example.NST.model.AcademicTitleHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcademicTitleHistoryRepository extends JpaRepository
        <AcademicTitleHistory, Long> {
    List<AcademicTitleHistory> findByMemberId(Long memberId);
}
