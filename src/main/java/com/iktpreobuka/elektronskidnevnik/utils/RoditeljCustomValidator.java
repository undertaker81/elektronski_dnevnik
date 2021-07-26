package com.iktpreobuka.elektronskidnevnik.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iktpreobuka.elektronskidnevnik.entities.Nastavnik;
import com.iktpreobuka.elektronskidnevnik.entities.Roditelj;
@Component
public class RoditeljCustomValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Nastavnik.class.equals(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		Roditelj roditelj = (Roditelj) target;
		if(roditelj.getPassword().equals(roditelj.getPassword())) {
			errors.reject("400", "Passwords must match");
		}
	}
}
