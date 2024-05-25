package com.example.NST.service;

import com.example.NST.converter.impl.*;
import com.example.NST.dto.*;
import com.example.NST.model.*;
import com.example.NST.model.enumeration.MemberRole;
import com.example.NST.repository.*;
import com.example.NST.validator.MemberDependencyValidator;
import com.example.NST.validator.MemberRoleValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.NST.model.enumeration.MemberRole.*;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;
    private final AcademicTitleMemberDTOConverter academicTitleMemberDTOConverter;
    private final AcademicTitleConverter academicTitleConverter;
    private final ScientificFieldConverter scientificFieldConverter;
    private final DepartmentRepository departmentRepository;
    private final AcademicTitleRepository academicTitleRepository;
    private final EducationTitleRepository educationTitleRepository;
    private final ScientificFieldRepository scientificFieldRepository;
    private final AcademicTitleHistoryRepository academicTitleHistoryRepository;
    private final MemberHistoryRepository memberHistoryRepository;
    private final RoleChangeMemberDTOConverter roleChangeConverter;
    private final DepartmentChangeMemberDTOConverter departmentChangeMemberConverter;
    private final MemberRoleValidator memberRoleValidator;
    private final MemberDependencyValidator validator;
    private final AcademicTitleHistoryConverter academicTitleHistoryConverter;
    private final MemberHistoryConverter memberHistoryConverter;

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberConverter memberConverter, AcademicTitleMemberDTOConverter academicTitleMemberDTOConverter, AcademicTitleConverter academicTitleConverter, ScientificFieldConverter scientificFieldConverter, DepartmentRepository departmentRepository, AcademicTitleRepository academicTitleRepository, EducationTitleRepository educationTitleRepository, ScientificFieldRepository scientificFieldRepository, AcademicTitleHistoryRepository academicTitleHistoryRepository, MemberHistoryRepository memberHistoryRepository, RoleChangeMemberDTOConverter roleChangeConverter, DepartmentChangeMemberDTOConverter departmentChangeMemberConverter, MemberRoleValidator memberRoleValidator, MemberDependencyValidator memberDependencyValidator, AcademicTitleHistoryConverter academicTitleHistoryConverter, MemberHistoryConverter memberHistoryConverter) {
        this.memberRepository = memberRepository;
        this.memberConverter = memberConverter;
        this.academicTitleMemberDTOConverter = academicTitleMemberDTOConverter;
        this.academicTitleConverter = academicTitleConverter;
        this.scientificFieldConverter = scientificFieldConverter;
        this.departmentRepository = departmentRepository;
        this.academicTitleRepository = academicTitleRepository;
        this.educationTitleRepository = educationTitleRepository;
        this.scientificFieldRepository = scientificFieldRepository;
        this.academicTitleHistoryRepository = academicTitleHistoryRepository;
        this.memberHistoryRepository = memberHistoryRepository;
        this.roleChangeConverter = roleChangeConverter;
        this.departmentChangeMemberConverter = departmentChangeMemberConverter;
        this.memberRoleValidator = memberRoleValidator;
        this.validator = memberDependencyValidator;
        this.academicTitleHistoryConverter = academicTitleHistoryConverter;
        this.memberHistoryConverter = memberHistoryConverter;
    }

    @Transactional
    public MemberDTO save(MemberDTO memberDTO) throws Exception {
        Member memberToSave = memberConverter.toEntity(memberDTO);

        if(memberToSave.getRole().equals(SUPERVISOR) ||
                memberToSave.getRole().equals(SECRETARY)){
            throw new Exception("The member you are trying to save is a director or secretary." +
                    "This endpoint is only concerned with adding new regular members." +
                    "To add a custom member, please refer to a different endpoint.");
        }

        Optional<Member> testingMemberOpt =
                memberRepository.findByFirstNameAndLastName(
                        memberToSave.getFirstName(),
                        memberToSave.getLastName());

        if(testingMemberOpt.isPresent()){
            throw new Exception("This member already exists. If you're trying to " +
                    "update something about this user please try a different endpoint." +
                    "This endpoint is only concerned with adding new members.");
        }

        Department newDept = memberToSave.getDepartment();
        AcademicTitle newAcTitle = memberToSave.getAcademicTitle();
        EducationTitle newEducTitle = memberToSave.getEducationTitle();
        ScientificField newSciField = memberToSave.getScientificField();

        validator.validateMemberDependency(memberToSave.getDepartment());
        validator.validateMemberDependency(memberToSave.getAcademicTitle());
        validator.validateMemberDependency(memberToSave.getEducationTitle());
        validator.validateMemberDependency(memberToSave.getScientificField());

        if(!departmentRepository
                .existsById(memberToSave.getDepartment().getId())) {
            newDept = departmentRepository.save(memberToSave.getDepartment());
        }

        if(!academicTitleRepository
                .existsById(memberToSave.getAcademicTitle().getId())) {
            newAcTitle = academicTitleRepository.save(memberToSave.getAcademicTitle());
        }

        if(!educationTitleRepository
                .existsById(memberToSave.getEducationTitle().getId())) {
           newEducTitle = educationTitleRepository.save(memberToSave.getEducationTitle());
        }

        if(!scientificFieldRepository
                .existsById(memberToSave.getScientificField().getId())) {
            newSciField = scientificFieldRepository.save(memberToSave.getScientificField());
        }

        memberToSave.setDepartment(newDept);
        memberToSave.setAcademicTitle(newAcTitle);
        memberToSave.setEducationTitle(newEducTitle);
        memberToSave.setScientificField(newSciField);

        Member savedMember = memberRepository.save(memberToSave);

        return memberConverter.toDto(savedMember);
    }



    public void delete(Long id) throws Exception {
        if(id == null){
            throw new Exception("Id must not be null");
        }
        var memberOptDb =
                memberRepository.findById(id);
        if(memberOptDb.isEmpty()){
            throw new Exception("There is no member with " +
                    "given id.");
        }
        Member member = memberOptDb.get();
        if(member.getRole().equals(SECRETARY) ||
            member.getRole().equals(SUPERVISOR)) {
            throw new Exception("You may not delete a secretary or a " +
                    "director. The proper way to handle director and secretary " +
                    "changes is to update their roles to member first, then delete" +
                    "them. This requires a new member to take their spot as director " +
                    "or secretary.");
        }

        memberRepository.deleteById(id);
    }

    @Transactional
    protected void validateUpdateAcademicTitleInput(AcademicTitleMemberDTO member)
                    throws Exception{
        Long id = member.getId();

        if(id == null){
            throw new Exception("You must give member's id.");
        }

        if(member.getAcademicTitleDTO() == null){
            throw new Exception("You must input a valid academic title");
        }

        String academicTitleName =
                member.getAcademicTitleDTO().getTitleName();

        if(academicTitleName == null || academicTitleName.isEmpty()){
            throw new Exception("Your academic title must have a title" +
                    " name.");
        }

        if(member.getScientificFieldDTO() == null){
            throw new Exception("You must input a valid scientific field");
        }

        String scientificFieldName =
                member.getScientificFieldDTO().getFieldName();

        if(scientificFieldName == null || scientificFieldName.isEmpty()){
            throw new Exception("Your scientific field must have a proper" +
                    "name");
        }
    }

    @Transactional
    public AcademicTitleMemberDTO updateAcademicTitle(AcademicTitleMemberDTO member) throws Exception {

        this.validateUpdateAcademicTitleInput(member);

        Optional<Member> memberOptDb = memberRepository
                .findById(member.getId());

        if(memberOptDb.isEmpty()){
            throw new Exception("This member may not exist.");
        }

        Member memberForUpdate = memberOptDb.get();

        validator.validateMemberDependency(member.getAcademicTitleDTO());
        validator.validateMemberDependency(member.getScientificFieldDTO());

        AcademicTitle academicTitle = academicTitleConverter.toEntity(
                member.getAcademicTitleDTO());
        ScientificField scientificField = scientificFieldConverter.toEntity(
                member.getScientificFieldDTO());

        if(!academicTitleRepository
                .existsById(member.getAcademicTitleDTO().getId())) {
            academicTitle = academicTitleRepository.save(
                    academicTitleConverter.toEntity(member.getAcademicTitleDTO()));
        }

        if(!scientificFieldRepository
                .existsById(member.getScientificFieldDTO().getId())) {
            scientificField = scientificFieldRepository.save(
                    scientificFieldConverter.toEntity(member.getScientificFieldDTO()));
        }

                LocalDate startDate =
                        memberForUpdate.getStartDate();
                LocalDate endDate =
                        LocalDate.now();

                AcademicTitleHistory historyToSave =
                        new AcademicTitleHistory(null, startDate, endDate,
                                                 memberForUpdate,
                                                 memberForUpdate.getAcademicTitle(),
                                                 memberForUpdate.getScientificField());
                academicTitleHistoryRepository.save(historyToSave);

        memberForUpdate.setAcademicTitle(academicTitle);
        memberForUpdate.setScientificField(scientificField);
        memberForUpdate.setStartDate(endDate);
        var savedMember = memberRepository.save(memberForUpdate);

        return academicTitleMemberDTOConverter.toDto(savedMember);
    }

    @Transactional
    protected Member fetchMemberIfInputValid (RoleChangeMemberDTO memberDTO) throws Exception{
        if(memberDTO.getId() == null){
            throw new Exception("You must input an id for member role change.");
        }

        if(memberDTO.getNewRole().equals(REGULAR)){
            throw new Exception("You may not degrade a current secretary or director to " +
                    "a regular member. Downgrading happens automatically when a new director or " +
                    "secretary are chosen. To downgrade this member, please provide a new replacement.");
        }

        return this.getMember(memberDTO);
    }

    @Transactional
    protected Department fetchDepartmentIfInputValid(Member memberDb) throws Exception {
        final Optional<Department> departmentForRoleChange =
                departmentRepository.findById(memberDb.getDepartment().getId());

        if(departmentForRoleChange.isEmpty()){
            throw new Exception("The department this member has been assigned to " +
                    "seems to be invalid. Please update member to proceed.");
        }

        return departmentForRoleChange.get();
    }

    @Transactional
    protected Member fetchCurrentRoleHolderIfUnique(MemberRole role, Long departmentId)
                                                                    throws Exception{
        final List<Member> currentRoleHolderList =
                memberRepository.findRoleHolder(role,departmentId);
        if(currentRoleHolderList.size() != 1){
            throw new Exception("This department has more than one " +
                    role.toString().toUpperCase() + ". Please address this by " +
                    "removing one of them manually.");
        }

        return currentRoleHolderList.get(0);
    }

    private MemberRole getOppositeChairmanRole(MemberRole role){
        return role.equals(SUPERVISOR) ? SECRETARY : SUPERVISOR;
    }

    @Transactional
    public RoleChangeMemberDTO updateRole(RoleChangeMemberDTO memberDTO) throws Exception {

        var memberDb = this.fetchMemberIfInputValid(memberDTO);

        if (memberDb.getDepartment().getId() == null) {
            throw new Exception("This member may not be assigned to any " +
                    "department. Please fix this error by updating said member" +
                    "before proceeding.");
        }

        var departmentDb = fetchDepartmentIfInputValid(memberDb);

        Member currentRoleHolder =
                this.fetchCurrentRoleHolderIfUnique(memberDTO.getNewRole()
                        ,departmentDb.getId());

        this.retireCurrentChairman(currentRoleHolder);

        switch (memberDb.getRole()){
            case REGULAR:
                currentRoleHolder.setRole(MemberRole.REGULAR);
                memberRepository.save(currentRoleHolder);
                break;
            case SUPERVISOR:
            case SECRETARY:
                var oppositeRole = getOppositeChairmanRole(
                        memberDb.getRole());
                var oppositeRoleHolder =
                        this.fetchCurrentRoleHolderIfUnique(
                                oppositeRole,
                                departmentDb.getId());

                oppositeRoleHolder.setRole(memberDb.getRole());
                memberRepository.save(oppositeRoleHolder);
        }

        memberDb.setRole(memberDTO.getNewRole());
        memberDb.setStartDate(LocalDate.now());
        var newRoleHolder =
                memberRepository.save(memberDb);

        return roleChangeConverter.toDto(newRoleHolder);

    }

    @Transactional
    protected void retireCurrentChairman(Member currentRoleHolder){
        LocalDate startDate = currentRoleHolder.getStartDate();
        LocalDate endDate = LocalDate.now();
        MemberRole role = currentRoleHolder.getRole();
        Department department = currentRoleHolder
                .getDepartment();

        MemberHistory historyToSave =
                new MemberHistory(
                        null, startDate, endDate,
                        role, department, currentRoleHolder);

        memberHistoryRepository.save(historyToSave);
    }

    @Transactional
    protected Member getMember(RoleChangeMemberDTO memberDTO) throws Exception {

        Optional<Member> memberOptDb =
                memberRepository.findById(memberDTO.getId());

        if(memberOptDb.isEmpty()) {
            throw new Exception("There are no members with said id. If you wish to " +
                    "add a new member, please refer to a different endpoint.");
        }

        Member memberDb = memberOptDb.get();
        MemberRole newRole = memberDTO.getNewRole();

        if((memberDb.getRole().equals(REGULAR) &&
            newRole.equals(REGULAR)) ||
                (memberDb.getRole().equals(SUPERVISOR) &&
                        newRole.equals(SUPERVISOR)) ||
                (memberDb.getRole().equals(SECRETARY) &&
                        newRole.equals(SECRETARY))) {
            throw new Exception("There is no need for a role change. " +
                    "User with given id already has that role.");
        }
        return memberDb;
    }

    public MemberDTO queryById(Long id) throws Exception {

        if(id == null){
            throw new Exception("Id must not be null.");
        }

        Optional<Member> memberDbOpt =
                memberRepository.findById(id);

        if(memberDbOpt.isEmpty()){
            throw new Exception("Member with given id not found.");
        }

        Member savedMember = memberDbOpt.get();

        return memberConverter.toDto(savedMember);
    }

    public List<MemberDTO> getAllOfType(String type) throws Exception {
        memberRoleValidator.validateRole(type);

        return listOfMembersToListToDTOs(
                memberRepository.findAllByType(MemberRole.valueOf(
                        type.toUpperCase())));
    }

    public List<MemberDTO> getAll(Pageable pageable) {
        return listOfMembersToListToDTOs(
                memberRepository.findAll(pageable).getContent());
    }

    private List<MemberDTO> listOfMembersToListToDTOs(
            List<Member> members) {

        List<MemberDTO> memberDTOS = new ArrayList<>();

        for(var m : members) {
            var memberDTO = memberConverter.toDto(m);
            memberDTOS.add(memberDTO);
        }

        return memberDTOS;
    }

    public DepartmentChangeMemberDTO updateDepartment(DepartmentChangeMemberDTO memberDTO)
                                                                        throws Exception{
        if(memberDTO.getId() == null || memberDTO.getNewDepartmentId() == null){
            throw new Exception("DTO input invalid. Try again.");
        }

        Long memberId = memberDTO.getId();
        Long newDepartmentId = memberDTO.getNewDepartmentId();

        Optional<Member> memberOptDb =
                memberRepository.findById(memberId);

        if(memberOptDb.isEmpty()){
            throw new Exception("There is no member with given id.");
        }

        var memberToChange = memberOptDb.get();

        if(memberToChange.getRole().equals(SUPERVISOR) ||
            memberToChange.getRole().equals(SECRETARY)) {
            throw new Exception("You may not change the department of a " +
                    "sitting director or a secretary. This feature is only " +
                    "available for regular members.");
        }

        Optional<Department> departmentOptDb =
                departmentRepository.findById(newDepartmentId);

        if(departmentOptDb.isEmpty()){
            throw new Exception("There is no department with said id.");
        }

        var newDepartment = departmentOptDb.get();

        if(memberToChange.getDepartment() != null
                && (memberToChange.getDepartment().getId().longValue()
                == newDepartmentId.longValue())){
            throw new Exception("There is no need for a department change." +
                    " This member is already assigned to that department.");
        }

        memberToChange.setDepartment(newDepartment);
        Member memberSaved = memberRepository.save(memberToChange);
        return departmentChangeMemberConverter.toDto(memberSaved);

    }

    public List<AcademicTitleHistoryDTO> historiesForMember(Long memberId) {
        List<AcademicTitleHistoryDTO> historyDTOs = new ArrayList<>();
        List<AcademicTitleHistory> historiesForMemberId =
                academicTitleHistoryRepository.findByMemberId(memberId);

        for (var h : historiesForMemberId) {
            var historyDTO = academicTitleHistoryConverter.toDto(h);
            historyDTOs.add(historyDTO);
        }

        return historyDTOs;
    }

    public List<MemberHistoryDTO> getAllHistoriesFor(String memberRole) throws Exception {
        memberRoleValidator.validateRole(memberRole);

        List<MemberHistoryDTO> historyDTOs = new ArrayList<>();
        List<MemberHistory> historiesForMemberType =
                memberHistoryRepository.findAllByType(
                        MemberRole.valueOf(memberRole.toUpperCase()));

        for (var h : historiesForMemberType) {
            var historyDTO = memberHistoryConverter.toDto(h);
            historyDTOs.add(historyDTO);
        }

        return historyDTOs;
    }
}
