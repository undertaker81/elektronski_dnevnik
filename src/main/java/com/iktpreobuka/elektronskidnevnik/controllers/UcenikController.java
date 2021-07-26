package com.iktpreobuka.elektronskidnevnik.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronskidnevnik.entities.Ucenik;
import com.iktpreobuka.elektronskidnevnik.repositories.PredmetRepository;
import com.iktpreobuka.elektronskidnevnik.repositories.UcenikRepository;
import com.iktpreobuka.elektronskidnevnik.utils.UcenikCustomValidator;

@RestController
@RequestMapping(value = "/api/v1/ucenici")
public class UcenikController {

	@Autowired
	private UcenikRepository ucenikRepository;

	@Autowired
	private UcenikCustomValidator ucenikValidator;
	
	@Autowired
	private PredmetRepository predmetRepository;

	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.addValidators(ucenikValidator);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/")
	public ResponseEntity<?> ucenik(@Valid @RequestParam String name, @RequestParam String lastName,
			@RequestParam String userName, @RequestParam String password) {
		Ucenik ucenik = new Ucenik();
		ucenik.setName(name);
		ucenik.setLastName(lastName);
		ucenik.setUsername(userName);
		ucenik.setPassword(password);
		ucenikRepository.save(ucenik);
		return new ResponseEntity<>(ucenik, HttpStatus.CREATED);
	}
	
}
