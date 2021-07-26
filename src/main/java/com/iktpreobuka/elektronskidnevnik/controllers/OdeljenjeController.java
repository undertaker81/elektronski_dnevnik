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

import com.iktpreobuka.elektronskidnevnik.entities.Nastavnik;
import com.iktpreobuka.elektronskidnevnik.entities.Odeljenje;
import com.iktpreobuka.elektronskidnevnik.entities.Predaje;
import com.iktpreobuka.elektronskidnevnik.entities.PredajeOdeljenju;
import com.iktpreobuka.elektronskidnevnik.entities.Predmet;
import com.iktpreobuka.elektronskidnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.elektronskidnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.elektronskidnevnik.repositories.PredajeOdeljenjuRepository;
import com.iktpreobuka.elektronskidnevnik.repositories.PredajeRepository;
import com.iktpreobuka.elektronskidnevnik.repositories.PredmetRepository;
import com.iktpreobuka.elektronskidnevnik.utils.OdeljenjeCustomValidator;

@RestController
@RequestMapping(value = "/api/v1/odeljenja")
public class OdeljenjeController {

	@Autowired
	private OdeljenjeRepository odeljenjeRepository;
	
	@Autowired
	private OdeljenjeCustomValidator odeljenjeValidator;
	@Autowired
	private PredajeRepository predajeRepository;
	@Autowired
	private PredajeOdeljenjuRepository predajeOdeljenjuRepository;
	
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
	@RequestMapping(method = RequestMethod.PUT, value = "/odeljenja")
	public ResponseEntity<?> connectNastavnikOdeljenje(@RequestParam Integer predajeId, @RequestParam Integer odeljenjeId){
		PredajeOdeljenju predajeOdeljenju = new PredajeOdeljenju();
		Predaje predaje= predajeRepository.findById(predajeId).get();
		Odeljenje  odeljenje= odeljenjeRepository.findById(odeljenjeId).get();
		predajeOdeljenju.setPredaje(predaje);
		predajeOdeljenju.setOdeljenje(odeljenje);
		predajeOdeljenjuRepository.save(predajeOdeljenju);
		return new ResponseEntity<>(predajeOdeljenju,HttpStatus.CREATED);
		
	}
}
