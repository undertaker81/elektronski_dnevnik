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

import com.iktpreobuka.elektronskidnevnik.entities.Roditelj;
import com.iktpreobuka.elektronskidnevnik.entities.Ucenik;
import com.iktpreobuka.elektronskidnevnik.repositories.RoditeljRepository;
import com.iktpreobuka.elektronskidnevnik.repositories.RoleRepository;
import com.iktpreobuka.elektronskidnevnik.repositories.UcenikRepository;
import com.iktpreobuka.elektronskidnevnik.utils.RoditeljCustomValidator;

@RestController
@RequestMapping(value = "/api/v1/roditelji")
public class RoditeljController {

	@Autowired
	private RoditeljRepository roditeljRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private RoditeljCustomValidator roditeljValidator;
	
	@Autowired
	private UcenikRepository ucenikRepository;
	
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.addValidators(roditeljValidator);
	}
	//@Validated
	@RequestMapping(method = RequestMethod.POST, value = "/")
	public ResponseEntity<?> createRoditelj( @RequestParam String name, @RequestParam String lastName,
	@RequestParam String userName, @RequestParam String password,@RequestParam String email) {
		Roditelj roditelj = new Roditelj();
		roditelj.setName(name);
		roditelj.setLastName(lastName);
		roditelj.setUsername(userName);
		roditelj.setPassword(password);
		roditelj.setEmail(email);
		roditelj.setRole(roleRepository.findById(4).get());
		roditeljRepository.save(roditelj);
		return new ResponseEntity<>(roditelj, HttpStatus.CREATED);	
	}
	@RequestMapping(method = RequestMethod.PUT, value = "/deca")
	public ResponseEntity<?> connectUcenikSaRoditeljem(@RequestParam Integer ucenikId, @RequestParam Integer roditeljId){
		Ucenik ucenik= ucenikRepository.findById(ucenikId).get();
		Roditelj roditelj = roditeljRepository.findById(roditeljId).get();
		ucenik.setRoditelj(roditelj);
		ucenikRepository.save(ucenik);
		return new ResponseEntity<>(ucenik,HttpStatus.CREATED);
		
	}
}
