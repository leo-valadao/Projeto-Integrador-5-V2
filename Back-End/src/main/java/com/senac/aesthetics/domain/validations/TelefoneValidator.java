package com.senac.aesthetics.domain.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.senac.aesthetics.domain.anotations.Telefone;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TelefoneValidator implements ConstraintValidator<Telefone, Object> {

    private Pattern padrao = Pattern.compile("\\(\\d{2}\\)(\\d)?\\d{4}\\-\\d{4}");

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext arg1) {
        if (value == null || value.equals("")) {
			return true;
		}

		Matcher matcher = padrao.matcher(value.toString());
		return matcher.matches();
    }
}
