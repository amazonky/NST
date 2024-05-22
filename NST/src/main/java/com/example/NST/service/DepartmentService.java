package com.example.NST.service;

import com.example.NST.converter.impl.DepartmentConverter;
import com.example.NST.dto.DepartmentDTO;
import com.example.NST.model.Department;
import com.example.NST.repository.DepartmentRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentConverter departmentConverter;
    @Autowired
    private DepartmentRepository departmentRepository;


    public DepartmentDTO save(DepartmentDTO departmentDto) throws Exception {
        Optional<Department> dept = departmentRepository.findByName(departmentDto.getName());
        if (dept.isPresent()) {
            throw new EntityExistsException("Department sa tim imenom postoji!");
        } else {
            //DTO - > Entity
            //Department department = new Department(departmentDto.getId(), departmentDto.getName());
            Department department = departmentConverter.toEntity(departmentDto);
            department = departmentRepository.save(department);
            return departmentConverter.toDto(department);
        }
    }

    public void delete(Long id) throws Exception {
        //proveri da li postoji department
        Optional<Department> dept = departmentRepository.findById(id);
        if (dept.isPresent()) {
            //postoji
            Department department = dept.get();
            departmentRepository.delete(department);
        } else {
            throw new Exception("Department does not exist!");
        }

    }

    public DepartmentDTO update(DepartmentDTO department) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public DepartmentDTO findById(Long id) throws Exception {
        Optional<Department> dept = departmentRepository.findById(id);
        if (dept.isPresent()) {
            //postoji
            Department department = dept.get();
            return departmentConverter.toDto(department);
        } else {
            throw new Exception("Department does not exist!");
        }
    }

    public List<DepartmentDTO> getAll(Pageable pageable) {
        List<Department> departments = departmentRepository.findAll(pageable).getContent();
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();

        for(Department d: departments) {
            DepartmentDTO departmentDTO = departmentConverter.toDto(d);
            departmentDTOS.add(departmentDTO);
        }

        return departmentDTOS;
    }

}
