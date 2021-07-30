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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PredajeOdeljenju {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Predaje predaje;
	@JoinColumn
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Odeljenje odeljenje;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Predaje getPredaje() {
		return predaje;
	}

	public void setPredaje(Predaje predaje) {
		this.predaje = predaje;
	}

	public Odeljenje getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(Odeljenje odeljenje) {
		this.odeljenje = odeljenje;
	}

	public PredajeOdeljenju(Integer id, Predaje predaje, Odeljenje odeljenje) {
		super();
		this.id = id;
		this.predaje = predaje;
		this.odeljenje = odeljenje;
	}

	public PredajeOdeljenju() {
		super();
		// TODO Auto-generated constructor stub
	}

}
