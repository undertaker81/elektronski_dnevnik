package com.iktpreobuka.elektronskidnevnik.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Odeljenje { // ClassEntity

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<PredajeOdeljenju> predajeOdeljenju;
	@JoinColumn
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Nastavnik razredni;
	@Column(name = "brojRazreda")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String brojRazreda;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<PredajeOdeljenju> getPredajeOdeljenju() {
		return predajeOdeljenju;
	}

	public void setPredajeOdeljenju(List<PredajeOdeljenju> predajeOdeljenju) {
		this.predajeOdeljenju = predajeOdeljenju;
	}

	public Nastavnik getRazredni() {
		return razredni;
	}

	public void setRazredni(Nastavnik razredni) {
		this.razredni = razredni;
	}

	public String getBrojRazreda() {
		return brojRazreda;
	}

	public void setBrojRazreda(String brojRazreda) {
		this.brojRazreda = brojRazreda;
	}

	public Odeljenje(Integer id, List<PredajeOdeljenju> predajeOdeljenju, Nastavnik razredni, String brojRazreda) {
		super();
		this.id = id;
		this.predajeOdeljenju = predajeOdeljenju;
		this.razredni = razredni;
		this.brojRazreda = brojRazreda;
	}

	public Odeljenje() {
		super();
		// TODO Auto-generated constructor stub
	}

}
