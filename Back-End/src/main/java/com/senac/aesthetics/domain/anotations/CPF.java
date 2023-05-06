package com.senac.aesthetics.domain.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.senac.aesthetics.domain.validations.CPFValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = CPFValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CPF {

    String message() default "Formato de CPF inválido!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
