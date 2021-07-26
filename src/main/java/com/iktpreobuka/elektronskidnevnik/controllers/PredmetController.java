package com.iktpreobuka.elektronskidnevnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronskidnevnik.entities.Predmet;
import com.iktpreobuka.elektronskidnevnik.repositories.PredmetRepository;

@RestController
@RequestMapping(value = "/api/v1/predmeti")
public class PredmetController {

	@Autowired
	private PredmetRepository predmetRepository;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/")
	public ResponseEntity<?> createPredmet(@RequestParam String imeCasa, @RequestParam Integer fondCasova){
		Predmet predmet = new Predmet();
		predmet.setImeCasa(imeCasa);
		predmet.setFondCasova(fondCasova);
		predmetRepository.save(predmet);
		return new ResponseEntity<>(predmet, HttpStatus.CREATED);	
	}
	
}
