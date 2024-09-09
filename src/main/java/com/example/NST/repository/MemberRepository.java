package com.example.NST.repository;

import com.example.NST.model.Member;
import com.example.NST.model.enumeration.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByFirstNameAndLastName(String firstName, String lastName);
    @Query("SELECT e FROM Member e WHERE e.role = :value1 AND e.department.id = :value2")
    List<Member> findRoleHolder(@Param("value1") MemberRole memberRole, @Param("value2") Long id);

    @Query("SELECT e FROM Member e WHERE e.role = :type")
    List<Member> findAllByType(@Param("type") MemberRole type);
}
