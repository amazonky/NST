package com.example.NST.controller;

import com.example.NST.dto.EducationTitleDTO;
import com.example.NST.service.EducationTitleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education-title")
public class EducationTitleController {
    @Autowired
    private EducationTitleService educationTitleService;

    @PostMapping("/save")
    public ResponseEntity<EducationTitleDTO> save(@Valid @RequestBody
                                                  EducationTitleDTO educationTitleDTO) throws Exception{
        var educationTitleFromServiceDTO =
                educationTitleService.save(educationTitleDTO);
        return ResponseEntity.ok(educationTitleFromServiceDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception{
        educationTitleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all/pageable")
    public ResponseEntity<List<EducationTitleDTO>> getAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "3") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortingCriterium,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortingDirection
    ){
        Pageable pageable = null;
        if (sortingDirection.equalsIgnoreCase(
                "desc")) {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortingCriterium).descending());
        } else {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortingCriterium).ascending());
        }

        return ResponseEntity.ok(educationTitleService.getAll(pageable));
    }
}
