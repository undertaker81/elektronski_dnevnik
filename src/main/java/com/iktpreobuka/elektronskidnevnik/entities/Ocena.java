package com.iktpreobuka.elektronskidnevnik.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ocena {

	@NotNull(message = "Ocena mora biti unesena!")
	@Min(value = 1, message = "Ocena ne moze biti manja od 1!")
	@Max(value = 5, message = "Ocena ne moze biti veca od 5!")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ocena")
	private Integer ocena;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@Column
	private Date datumUnosaOcene;
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Polugodiste Polugodiste;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private PredajeStudentuIzOdeljenja predajeUceniku;

	enum Polugodiste {
		PRVO, DRUGO
	}

	public Integer getOcena() {
		return ocena;
	}

	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}

	public Date getDatumUnosaOcene() {
		return datumUnosaOcene;
	}

	public void setDatumUnosaOcene(Date datumUnosaOcene) {
		this.datumUnosaOcene = datumUnosaOcene;
	}

	public Polugodiste getPolugodiste() {
		return Polugodiste;
	}

	public void setPolugodiste(Polugodiste polugodiste) {
		Polugodiste = polugodiste;
	}

	public PredajeStudentuIzOdeljenja getPredajeUceniku() {
		return predajeUceniku;
	}

	public void setPredajeUceniku(PredajeStudentuIzOdeljenja predajeUceniku) {
		this.predajeUceniku = predajeUceniku;
	}

	public Ocena(
			@NotNull(message = "Ocena mora biti unesena!") @Min(value = 1, message = "Ocena ne moze biti manja od 1!") @Max(value = 5, message = "Ocena ne moze biti veca od 5!") Integer ocena,
			Date datumUnosaOcene, com.iktpreobuka.elektronskidnevnik.entities.Ocena.Polugodiste polugodiste,
			PredajeStudentuIzOdeljenja predajeUceniku) {
		super();
		this.ocena = ocena;
		this.datumUnosaOcene = datumUnosaOcene;
		Polugodiste = polugodiste;
		this.predajeUceniku = predajeUceniku;
	}

	public Ocena() {
		super();
		// TODO Auto-generated constructor stub
	}

}