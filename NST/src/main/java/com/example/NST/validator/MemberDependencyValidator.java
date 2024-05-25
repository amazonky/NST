package com.example.NST.validator;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class MemberDependencyValidator {
    @Transactional
    public void validateMemberDependency(Object object) {
        if(object == null){
            throw new IllegalArgumentException("There is a null entity" +
                    " that is preventing" +
                    " the saving process");
        }
    }
}
