package com.senac.aesthetics.validations.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.senac.aesthetics.validations.validators.CPFCNPJValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = CPFCNPJValidator.class)
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CPFCNPJ {

  String message() default "Formato de CPF ou CNPJ Inválido!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
