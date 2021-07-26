package com.iktpreobuka.elektronskidnevnik.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronskidnevnik.entities.RoleEntity;
import com.iktpreobuka.elektronskidnevnik.entities.UserEntity;
import com.iktpreobuka.elektronskidnevnik.repositories.RoleRepository;
import com.iktpreobuka.elektronskidnevnik.repositories.UserRepository;
import com.iktpreobuka.elektronskidnevnik.utils.UserCustomValidator;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	 private UserCustomValidator userCustomValidator;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.addValidators(userCustomValidator);
	}

	@RequestMapping(path = "/users", method = RequestMethod.GET)	
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<List<UserEntity>>((List<UserEntity>) userRepository.findAll(), HttpStatus.OK);
	}
	
	List<UserEntity> users = new ArrayList<UserEntity>();
	
		// Dodaj novog korisnika
	@RequestMapping(path= "/users", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@Valid @RequestParam String name, @RequestParam String lastName,
			@RequestParam String userName, @RequestParam String password) {
		UserEntity user = new UserEntity();

		user.setName(name);
		user.setLastName(lastName);
		user.setUsername(userName);
		user.setPassword(password);
		userRepository.save(user);
		return new ResponseEntity<UserEntity>(user, HttpStatus.CREATED);
	}

	//Dodaj novu rolu
	
	@RequestMapping(method = RequestMethod.PUT, value = "/roles")
	public ResponseEntity<?> connectUserWithRole(@RequestParam Integer userId, @RequestParam Integer roleId){
		UserEntity user= userRepository.findById(userId).get(); 
			 RoleEntity role= roleRepository.findById(roleId).get();
			user.setRole(role);
			userRepository.save(user);	
		return new ResponseEntity<UserEntity>(user,HttpStatus.CREATED);
		
	}
}
