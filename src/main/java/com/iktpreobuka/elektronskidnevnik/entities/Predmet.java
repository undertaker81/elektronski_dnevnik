package com.iktpreobuka.elektronskidnevnik.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Predmet { // CourseEntity

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name = "imeCasa")
	@NotNull(message = "Ime casa ne moze biti prazno.")
	@Size(min = 3, max = 30, message = "Ime casa ne moze biti manje od 3 ni vece od 30 karaktera.")
	private String imeCasa;
	@Column(name = "fondCasova")
	private Integer fondCasova;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn
	private Predaje predaje;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImeCasa() {
		return imeCasa;
	}

	public void setImeCasa(String imeCasa) {
		this.imeCasa = imeCasa;
	}

	public Integer getFondCasova() {
		return fondCasova;
	}

	public void setFondCasova(Integer fondCasova) {
		this.fondCasova = fondCasova;
	}

	public Predaje getPredaje() {
		return predaje;
	}

	public void setPredaje(Predaje predaje) {
		this.predaje = predaje;
	}

	public Predmet(Integer id,
			@NotNull(message = "Ime casa ne moze biti prazno.") @Size(min = 3, max = 30, message = "Ime casa ne moze biti manje od 3 ni vece od 30 karaktera.") String imeCasa,
			Integer fondCasova, Predaje predaje) {
		super();
		this.id = id;
		this.imeCasa = imeCasa;
		this.fondCasova = fondCasova;
		this.predaje = predaje;
	}

	public Predmet() {
		super();
		// TODO Auto-generated constructor stub
	}

}
