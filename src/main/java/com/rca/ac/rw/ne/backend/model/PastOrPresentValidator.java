package com.rca.ac.rw.ne.backend.model;


import com.rca.ac.rw.ne.backend.model.dao.PastOrPresent;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class PastOrPresentValidator implements ConstraintValidator<PastOrPresent, LocalDateTime> {

    @Override
    public void initialize(PastOrPresent constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // You may want to handle null values differently
        }
        return !value.isAfter(LocalDateTime.now());
    }
}
