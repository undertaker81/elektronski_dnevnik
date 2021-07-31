package com.iktpreobuka.elektronskidnevnik.controllers;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronskidnevnik.entities.Ocena;
import com.iktpreobuka.elektronskidnevnik.entities.Polugodiste;
import com.iktpreobuka.elektronskidnevnik.entities.PredajeOdeljenju;
import com.iktpreobuka.elektronskidnevnik.entities.PredajeStudentuIzOdeljenja;
import com.iktpreobuka.elektronskidnevnik.entities.Ucenik;
import com.iktpreobuka.elektronskidnevnik.repositories.OcenaRepository;
import com.iktpreobuka.elektronskidnevnik.repositories.PredajeOdeljenjuRepository;
import com.iktpreobuka.elektronskidnevnik.repositories.PredajeStudentuIzOdeljenjaRepository;
import com.iktpreobuka.elektronskidnevnik.repositories.RoleRepository;
import com.iktpreobuka.elektronskidnevnik.repositories.UcenikRepository;
import com.iktpreobuka.elektronskidnevnik.services.EmailService;
import com.iktpreobuka.elektronskidnevnik.utils.UcenikCustomValidator;

@RestController
@RequestMapping(value = "/api/v1/ucenici")
public class UcenikController {

	@Autowired
	private UcenikRepository ucenikRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UcenikCustomValidator ucenikValidator;
	@Autowired
	protected PredajeOdeljenjuRepository predajeOdeljenjuRepository;

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	protected OcenaRepository ocenaRepository;
	@Autowired
	private PredajeStudentuIzOdeljenjaRepository predajeStudentuIzOdeljenjaRepository;

	@Autowired
	JavaMailSender emailSender;
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
		ucenik.setRole(roleRepository.findById(5).get());
		ucenikRepository.save(ucenik);
		return new ResponseEntity<>(ucenik, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/predajestudentu")
	public ResponseEntity<?> connectPredajeOdeljenjuIUcenik(@RequestParam Integer predajeOdeljenjuId,
			@RequestParam Integer ucenikId) {
		PredajeStudentuIzOdeljenja predajeUceniku = new PredajeStudentuIzOdeljenja();
		PredajeOdeljenju predajeOdeljenju = predajeOdeljenjuRepository.findById(predajeOdeljenjuId).get();
		Ucenik ucenik = ucenikRepository.findById(ucenikId).get();
		predajeUceniku.setPredajeOdeljenju(predajeOdeljenju);
		predajeUceniku.setUcenik(ucenik);
		predajeStudentuIzOdeljenjaRepository.save(predajeUceniku);
		return new ResponseEntity<>(predajeUceniku, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.POST, value =  "/ocene")
	public ResponseEntity<?> upisiOcenu(@RequestParam Integer ocena,
			@RequestParam Polugodiste polugodiste,@RequestParam Integer psoId){
		Ocena novaOcena =  new Ocena();
		novaOcena.setOcena(ocena);
		novaOcena.setDatumUnosaOcene(LocalDate.now());
		novaOcena.setPolugodiste(polugodiste);
PredajeStudentuIzOdeljenja predajeUcenikuOdeljenje= predajeStudentuIzOdeljenjaRepository.findById(psoId).get(); 
		novaOcena.setPredajeUceniku(predajeUcenikuOdeljenje);		
		ocenaRepository.save(novaOcena);
	
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(predajeUcenikuOdeljenje.getUcenik().getRoditelj().getEmail());
		message.setSubject("Vase dete je dobilo novu ocenu!");
		message.setText(("Ucenik " + predajeUcenikuOdeljenje.getUcenik().getName() + " " 
		+ predajeUcenikuOdeljenje.getUcenik().getLastName() + " " + "je dobilo ocenu "
				 + novaOcena.getOcena().toString() + " iz predmeta " + predajeUcenikuOdeljenje.getPredajeOdeljenju().getPredaje().getPredmet() +
				 " kod nastavnika " + predajeUcenikuOdeljenje.getPredajeOdeljenju().getPredaje().getNastavnik()));
		emailSender.send(message);
		return new ResponseEntity<>(novaOcena,HttpStatus.CREATED);
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/ucenik/{id}")
	public ResponseEntity<?> deleteUcenikById(@PathVariable Integer id){
	Ucenik ucenik = ucenikRepository.findById(id).get();
	ucenikRepository.delete(ucenik);
	return new ResponseEntity<>(ucenik,HttpStatus.OK);
	}
/*@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id){
		UserEntity user = userRepository.findById(id).get();
		userRepository.delete(user);
		return new ResponseEntity<>(user,HttpStatus.OK);*/
}
