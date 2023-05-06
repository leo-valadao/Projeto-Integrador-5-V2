package com.senac.aesthetics.domain.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.senac.aesthetics.domain.validations.TelefoneValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = TelefoneValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Telefone {

	String message() default "Formato de Telefone inválido!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
}
