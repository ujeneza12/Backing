package com.rca.ac.rw.ne.backend.model.dao;

import com.rca.ac.rw.ne.backend.model.AllowedEmailDomainsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AllowedEmailDomainsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowedEmailDomains {
    String message() default "Invalid email domain";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] domains();
}