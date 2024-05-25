package com.example.NST.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_academic_title_history")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AcademicTitleHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @PastOrPresent
    private LocalDate startDate;

    @NotNull
    @FutureOrPresent
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    @ManyToOne
    @JoinColumn(name = "academic_title_id")
    private AcademicTitle academicTitle;

    @ManyToOne
    @JoinColumn(name = "scientific_field_id")
    private ScientificField scientificField;

    @Override
    public String toString() {
        return "AcademicTitleHistory{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", member=" + (member == null ? null : member.getId()) +
                ", academicTitle=" + academicTitle +
                ", scientificField=" + scientificField +
                '}';
    }
}
