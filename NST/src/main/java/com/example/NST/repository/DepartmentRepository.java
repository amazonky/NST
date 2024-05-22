/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.NST.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.NST.model.Department;

import java.util.Optional;


public interface DepartmentRepository extends JpaRepository<Department, Long>{
    //vrati depratment na osnovu imena
    Optional<Department> findByName(String name);
}
