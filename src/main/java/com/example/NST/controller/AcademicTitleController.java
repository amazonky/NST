package com.example.NST.controller;

import com.example.NST.dto.AcademicTitleDTO;
import com.example.NST.service.AcademicTitleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academic-title")
public class AcademicTitleController {
    @Autowired
    private AcademicTitleService academicTitleService;

    @PostMapping("/save")
    public ResponseEntity<AcademicTitleDTO> save(@Valid @RequestBody
                                                     AcademicTitleDTO academicTitleDTO)
                                                        throws Exception{
        var academicTitleDTOResponse = academicTitleService.save(academicTitleDTO);
        return ResponseEntity.ok(academicTitleDTOResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) throws Exception{
        academicTitleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all/pageable")
    public ResponseEntity<List<AcademicTitleDTO>> findAll(
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "3") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortingCriterium,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection
    ){
        Pageable pageable = null;
        if (sortDirection.equalsIgnoreCase(
                "desc")) {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortingCriterium).descending());
        } else {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortingCriterium).ascending());
        }

        return ResponseEntity.ok(academicTitleService.getAll(pageable));
    }
}
