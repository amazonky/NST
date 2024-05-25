package com.example.NST.controller;

import com.example.NST.dto.*;
import com.example.NST.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/paging")
    public ResponseEntity<List<MemberDTO>> getAllPaging(
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "pageSize",defaultValue = "3") int pageSize,
        @RequestParam(name = "sortBy", defaultValue = "id") String sortingCriterium,
        @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {

        Pageable pageable = null;
        if (sortDirection.equalsIgnoreCase(
                "desc")) {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortingCriterium).descending());
        } else {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortingCriterium).ascending());
        }

        return ResponseEntity.ok(memberService.getAll(pageable));
    }

    @GetMapping("/queryById")
    public ResponseEntity<MemberDTO> queryById(@RequestParam("id") Long id) throws Exception {
        return ResponseEntity.ok(memberService.queryById(id));
    }

    @GetMapping("/queryByType")
    public ResponseEntity<List<MemberDTO>> getAllByRole(@RequestParam("role")
                                                        String role) throws Exception{
        return ResponseEntity.ok(memberService.getAllOfType(role));
    }

    @PostMapping("/save-regular")
    public ResponseEntity<MemberDTO> save(@Valid @RequestBody
                                          MemberDTO memberDTO) throws Exception{
        return ResponseEntity.ok(memberService.save(memberDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception{
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-academic-title")
    public ResponseEntity<AcademicTitleMemberDTO>
            updateAcademicTitle(@Valid @RequestBody AcademicTitleMemberDTO academicTitleMemberDTO) throws Exception{

        return ResponseEntity.ok(memberService
                .updateAcademicTitle(academicTitleMemberDTO));
    }

    @PatchMapping("/upgrade-role")
    public ResponseEntity<RoleChangeMemberDTO> updateRole(@Valid @RequestBody
                                                          RoleChangeMemberDTO memberDTO) throws Exception{
        return ResponseEntity.ok(memberService
                .updateRole(memberDTO));
    }

    @PatchMapping("/update-department")
    public ResponseEntity<DepartmentChangeMemberDTO> updateDepartment(
            @Valid @RequestBody DepartmentChangeMemberDTO memberDTO) throws Exception {
        return ResponseEntity.ok(memberService
                .updateDepartment(memberDTO));
    }

    @GetMapping("/academic-title-history-for")
    public ResponseEntity<List<AcademicTitleHistoryDTO>> allHistoriesForMember(
            @RequestParam("member-id") Long memberId) {
        return ResponseEntity.ok(memberService
                .historiesForMember(memberId));
    }

    @GetMapping("/histories-for-type")
    public ResponseEntity<List<MemberHistoryDTO>> findByType(
            @RequestParam String memberRole) throws Exception {

        return ResponseEntity.ok(memberService.getAllHistoriesFor(memberRole));
    }
}
