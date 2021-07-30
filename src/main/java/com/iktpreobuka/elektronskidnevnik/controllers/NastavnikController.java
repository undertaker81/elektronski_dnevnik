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

import com.iktpreobuka.elektronskidnevnik.entities.Nastavnik;
import com.iktpreobuka.elektronskidnevnik.entities.Predaje;
import com.iktpreobuka.elektronskidnevnik.entities.Predmet;
import com.iktpreobuka.elektronskidnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.elektronskidnevnik.repositories.PredajeRepository;
import com.iktpreobuka.elektronskidnevnik.repositories.PredmetRepository;
import com.iktpreobuka.elektronskidnevnik.repositories.RoleRepository;
import com.iktpreobuka.elektronskidnevnik.utils.NastavnikCustomValidator;

@RestController
@RequestMapping(value = "/api/v1/nastavnici")
public class NastavnikController {

	@Autowired
	private NastavnikRepository nastavnikRepository;
	@Autowired
	private NastavnikCustomValidator nastavnikValidator;
	@Autowired
	private PredajeRepository predajeRepository;
	@Autowired
	private PredmetRepository predmetRepository;
	@Autowired
	private RoleRepository roleRepository;

	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.addValidators(nastavnikValidator);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/")
	public ResponseEntity<?> createNastavnik(@Valid @RequestParam String name, @RequestParam String lastName,
			@RequestParam String userName, @RequestParam String password, @RequestParam String email) {

		Nastavnik nastavnik = new Nastavnik();
		nastavnik.setName(name);
		nastavnik.setLastName(lastName);
		nastavnik.setUsername(userName);
		nastavnik.setPassword(password);
		nastavnik.setEmail(email);
		nastavnik.setRole(roleRepository.findById(2).get());
		nastavnikRepository.save(nastavnik);
		return new ResponseEntity<>(nastavnik, HttpStatus.CREATED);
	}
	@RequestMapping(method = RequestMethod.PUT, value = "/predmeti")
	public ResponseEntity<?> connectNastavnikPredmet(@RequestParam Integer nastavnikId, @RequestParam Integer predmetId){
		Predaje predaje = new Predaje();
		Nastavnik nastavnik= nastavnikRepository.findById(nastavnikId).get();
		Predmet predmet = predmetRepository.findById(predmetId).get();
		predaje.setNastavnik(nastavnik);
		predaje.setPredmet(predmet);
		predajeRepository.save(predaje);
		return new ResponseEntity<>(predaje,HttpStatus.CREATED);
		
	}
}
