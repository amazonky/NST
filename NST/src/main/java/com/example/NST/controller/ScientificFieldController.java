package com.example.NST.controller;

import com.example.NST.dto.ScientificFieldDTO;
import com.example.NST.service.ScientificFieldService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scientific-field")
public class ScientificFieldController {
    @Autowired
    private ScientificFieldService scientificFieldService;

    @GetMapping("/all/pageable")
    public ResponseEntity<List<ScientificFieldDTO>> findAll(
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

        return ResponseEntity.ok(scientificFieldService.getAll(pageable));
    }

    @PostMapping("/save")
    public ResponseEntity<ScientificFieldDTO> save(@Valid @RequestBody
                                                   ScientificFieldDTO scientificFieldDTO) throws Exception{
        final var scientificFieldDtoFromService =
                scientificFieldService.save(scientificFieldDTO);
        return ResponseEntity.ok(scientificFieldDtoFromService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception{
        scientificFieldService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
