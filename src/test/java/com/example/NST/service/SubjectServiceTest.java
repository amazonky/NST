package com.example.NST.service;

import com.example.NST.converter.impl.SubjectConverter;
import com.example.NST.dto.SubjectDTO;
import com.example.NST.repository.DepartmentRepository;
import com.example.NST.repository.SubjectRepository;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.awt.print.Pageable;
import java.util.Optional;

import static com.example.NST.test_data.SubjectTestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SubjectServiceTest {
    @MockBean
    private SubjectRepository subjectRepository;
    @MockBean
    private DepartmentRepository departmentRepository;
    @MockBean
    private SubjectConverter subjectConverter;

    @Autowired
    private SubjectService service;

    @Test
    @DisplayName("Save subject normal test")
    void subjectSaveTest() throws Exception {
        when(subjectConverter.toEntity(WELL_FORMED_SUBJECT_DTO_WITH_ID))
                .thenReturn(WELL_FORMED_SUBJECT_WITH_ID);

        when(departmentRepository.findById(1L))
                .thenReturn(Optional
                        .of(WELL_FORMED_DEPARTMENT_WITH_ID));

        when(subjectRepository.save(WELL_FORMED_SUBJECT_WITH_ID))
                .thenReturn(WELL_FORMED_SUBJECT_WITH_ID);

        when(subjectConverter.toDto(WELL_FORMED_SUBJECT_WITH_ID))
                .thenReturn(WELL_FORMED_SUBJECT_DTO_WITH_ID);

        final SubjectDTO serviceResponse = service.save(WELL_FORMED_SUBJECT_DTO_WITH_ID);

        assertEquals(serviceResponse, WELL_FORMED_SUBJECT_DTO_WITH_ID);

    }

    @Test
    @DisplayName("Null department save test")
    void nullDeptTest() {
        when(subjectConverter.toEntity(NULL_DEPARTMENT_SUBJECT_DTO_WITH_ID))
                .thenReturn(NULL_DEPARTMENT_SUBJECT_WITH_ID);

        assertThatThrownBy(()->
                service.save(NULL_DEPARTMENT_SUBJECT_DTO_WITH_ID))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("The subject you are trying to save" +
                        "must contain a department.");
    }

    @Test
    @DisplayName("Saving of new department works.")
    void savingOfNewDepartmentSubjectSaveTest() throws Exception {
        when(subjectConverter.toEntity(NULL_ID_DEPARTMENT_SUBJECT_DTO_WITH_ID))
                .thenReturn(NULL_ID_DEPARTMENT_SUBJECT);

        when(subjectRepository.save(NULL_ID_DEPARTMENT_SUBJECT))
                .thenReturn(NULL_ID_DEPARTMENT_SUBJECT);

        when(subjectConverter.toDto(NULL_ID_DEPARTMENT_SUBJECT))
                .thenReturn(NULL_ID_DEPARTMENT_SUBJECT_DTO_WITH_ID);

        when(departmentRepository.save(NULL_ID_DEPARTMENT))
                .thenReturn(NULL_ID_DEPARTMENT);

        val serviceResponse = service.save(NULL_ID_DEPARTMENT_SUBJECT_DTO_WITH_ID);

        verify(departmentRepository, times(1)).save(NULL_ID_DEPARTMENT);

        assertEquals(NULL_ID_DEPARTMENT_SUBJECT_DTO_WITH_ID, serviceResponse);
    }

    @Test
    @DisplayName("Department does not exist by id")
    void departmentDoesNotExistById() throws Exception {
        when(subjectConverter.toEntity(UNKNOWN_ID_DEPARTMENT_SUBJECT_DTO_WITH_ID))
                .thenReturn(UNKNOWN_ID_DEPARTMENT_SUBJECT);

        when(departmentRepository.findById(-1L))
                .thenReturn(Optional.empty());

        when(departmentRepository.save(UNKNOWN_ID_DEPARTMENT))
                .thenReturn(WELL_FORMED_DEPARTMENT_WITH_ID);

        when(subjectRepository.save(UNKNOWN_ID_DEPARTMENT_SUBJECT))
                .thenReturn(UNKNOWN_ID_DEPARTMENT_SUBJECT);

        when(subjectConverter.toDto(UNKNOWN_ID_DEPARTMENT_SUBJECT))
                .thenReturn(UNKNOWN_ID_DEPARTMENT_SUBJECT_DTO_WITH_ID);

        val serviceResponse = service.save(UNKNOWN_ID_DEPARTMENT_SUBJECT_DTO_WITH_ID);

        assertEquals(serviceResponse, UNKNOWN_ID_DEPARTMENT_SUBJECT_DTO_WITH_ID);
    }



    @Test
    @DisplayName("Find by id test normal")
    void findByIdTest() throws Exception {
        when(subjectRepository.findById(1L))
                .thenReturn(Optional.of(WELL_FORMED_SUBJECT_WITH_ID));

        when(subjectConverter.toDto(WELL_FORMED_SUBJECT_WITH_ID))
                .thenReturn(WELL_FORMED_SUBJECT_DTO_WITH_ID);

        val serviceResponse = service.findById(1L);

        assertEquals(serviceResponse, WELL_FORMED_SUBJECT_DTO_WITH_ID);
    }


    @Test
    @DisplayName("Find by id not found test")
    void findByIdNotFoundTest() throws Exception {
        when(subjectRepository.findById(-1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(()->service.findById(-1L))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("Subject does not exist!");

    }
}
