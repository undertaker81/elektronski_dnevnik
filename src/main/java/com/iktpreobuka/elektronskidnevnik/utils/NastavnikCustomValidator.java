package com.iktpreobuka.elektronskidnevnik.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iktpreobuka.elektronskidnevnik.entities.Nastavnik;
@Component
public class NastavnikCustomValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Nastavnik.class.equals(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		Nastavnik nastavnik = (Nastavnik) target;
		if(nastavnik.getPassword().equals(nastavnik.getPassword())) {
			errors.reject("400", "Passwords must match");
		}
	}
}
