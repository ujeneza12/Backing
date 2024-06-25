package com.rca.ac.rw.ne.backend.model;

import com.rca.ac.rw.ne.backend.model.dao.AllowedEmailDomains;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
public class AllowedEmailDomainsValidator implements ConstraintValidator<AllowedEmailDomains, String> {

    private String[] allowedDomains;

    @Override
    public void initialize(AllowedEmailDomains constraintAnnotation) {
        this.allowedDomains = constraintAnnotation.domains();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) {
            return true;
        }
        return Arrays.stream(allowedDomains)
                .anyMatch(domain -> email.toLowerCase().endsWith("@" + domain));
    }
}