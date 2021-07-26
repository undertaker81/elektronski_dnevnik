package com.iktpreobuka.elektronskidnevnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronskidnevnik.entities.Odeljenje;
import com.iktpreobuka.elektronskidnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.elektronskidnevnik.utils.OdeljenjeCustomValidator;

@RestController
@RequestMapping(value = "/api/v1/odeljenja")
public class OdeljenjeController {

	@Autowired
	private OdeljenjeRepository odeljenjeRepository;
	
	@Autowired
	private OdeljenjeCustomValidator odeljenjeValidator;
	
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.addValidators(odeljenjeValidator);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/")
	public ResponseEntity<?> createOdeljenje(@RequestParam String brojRazreda){
		Odeljenje odeljenje = new Odeljenje();
		odeljenje.setBrojRazreda(brojRazreda);
		odeljenjeRepository.save(odeljenje);
		return  new ResponseEntity<>(odeljenje, HttpStatus.CREATED);
		
	}
}
