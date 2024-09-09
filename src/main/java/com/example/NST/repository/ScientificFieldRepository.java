package com.example.NST.repository;

import com.example.NST.model.ScientificField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScientificFieldRepository extends JpaRepository<ScientificField, Long> {
    Optional<ScientificField> findByFieldName(String scientificFieldName);
}
