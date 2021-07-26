package com.iktpreobuka.elektronskidnevnik.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iktpreobuka.elektronskidnevnik.entities.Odeljenje;

@Component
public class OdeljenjeCustomValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Odeljenje.class.equals(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		Odeljenje odeljenje = (Odeljenje) target;
		if (odeljenje.getBrojRazreda().equals(odeljenje.getBrojRazreda())) {
			errors.reject("400", "Broj razreda je vec u bazi");
		}
	}
}
