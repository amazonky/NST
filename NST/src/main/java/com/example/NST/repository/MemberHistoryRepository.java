package com.example.NST.repository;

import com.example.NST.model.MemberHistory;
import com.example.NST.model.enumeration.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MemberHistoryRepository
        extends JpaRepository<MemberHistory, Long> {

    @Query("SELECT e FROM MemberHistory e WHERE e.role = :value")
    List<MemberHistory> findAllByType(@Param("value") MemberRole memberRole);
}
