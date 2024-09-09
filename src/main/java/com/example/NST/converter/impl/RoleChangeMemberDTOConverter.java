package com.example.NST.converter.impl;

import com.example.NST.converter.DTOEntityConverter;
import com.example.NST.dto.RoleChangeMemberDTO;
import com.example.NST.model.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RoleChangeMemberDTOConverter implements
        DTOEntityConverter<RoleChangeMemberDTO, Member> {
    @Override
    public RoleChangeMemberDTO toDto(Member e) {
        if(e == null) return null;

        return new RoleChangeMemberDTO(
                e.getId(), e.getRole());
    }

    @Override
    public Member toEntity(RoleChangeMemberDTO t) {
        if(t == null) return null;

        return Member.builder()
                .id(t.getId())
                .role(t.getNewRole())
                .build();
    }
}
