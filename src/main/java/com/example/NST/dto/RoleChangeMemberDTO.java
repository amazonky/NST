package com.example.NST.dto;

import com.example.NST.model.enumeration.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleChangeMemberDTO {
    private Long id;
    private MemberRole newRole;
}
