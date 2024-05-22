package com.example.NST.controller;

import com.example.NST.dto.DepartmentDTO;
import com.example.NST.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/save")
    public ResponseEntity<DepartmentDTO> save(@Valid @RequestBody DepartmentDTO departmentDto) throws Exception {
        //ResponseEntity
        DepartmentDTO deptDto = departmentService.save(departmentDto);
        return new ResponseEntity<>(deptDto, HttpStatus.CREATED);
    }

   
    @GetMapping("/all/pageable")
    public ResponseEntity<List<DepartmentDTO>> getAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "3") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortingCriterium,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortingDirection
    ) {
        Pageable pageable = null;
        if (sortingDirection.equalsIgnoreCase(
                "desc")) {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortingCriterium).descending());
        } else {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortingCriterium).ascending());
        }

        return ResponseEntity.ok(departmentService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public DepartmentDTO findById(@PathVariable("id") Long id) throws Exception {
        System.out.println("Controller: " + id);
        return departmentService.findById(id);
    }

    @GetMapping("/query")
    public ResponseEntity<DepartmentDTO> queryById(@RequestParam("id") Long id) throws Exception {
        //return departmentService.findById(id);
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {

        departmentService.delete(id);
        return ResponseEntity.noContent().build();

    }

//    @ExceptionHandler(DepartmentAlreadyExistException.class)
//    public ResponseEntity<MyErrorDetails> handleException(DepartmentAlreadyExistException e) {
//        System.out.println("nst.springboot.restexample01.controller.DepartmentController.handleException()");
//        System.out.println("-----------pozvana metoda za obradu izuzetka u kontroleru -------------");
//
//        MyErrorDetails myErrorDetails = new MyErrorDetails(e.getMessage());
//
//        return new ResponseEntity<>(myErrorDetails, HttpStatus.BAD_REQUEST);
//
//    }
}
