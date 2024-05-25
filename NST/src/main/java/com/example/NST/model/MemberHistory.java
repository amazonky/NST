package com.example.NST.model;

import com.example.NST.model.enumeration.MemberRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_member_history")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "start_date")
    @NotNull
//    @PastOrPresent
    private LocalDate startDate;

    @Column(name="end_date")
    @NotNull
//    @FutureOrPresent
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
