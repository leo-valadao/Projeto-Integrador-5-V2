package com.senac.aesthetics.validations.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.senac.aesthetics.validations.CNPJValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = CNPJValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CNPJ {

    String message() default "Formato de CNPJ Inválido!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
