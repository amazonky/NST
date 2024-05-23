package com.example.NST.model.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MemberRole {

    REGULAR("REGULAR"),
    SUPERVISOR("SUPERVISOR"),
    SECRETARY("SECRETARY");

    private final String value;
}