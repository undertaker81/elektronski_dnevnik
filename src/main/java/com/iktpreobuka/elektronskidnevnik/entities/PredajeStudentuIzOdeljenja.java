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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PredajeStudentuIzOdeljenja { // StudentTeacherCourse

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer psoId;
	@JoinColumn
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private PredajeOdeljenju predajeOdeljenju;
	@JoinColumn
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Ucenik ucenik;
	@JsonIgnore
	@JsonBackReference
	@OneToMany(mappedBy = "predajeUceniku", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<Ocena> ocena;

	public PredajeStudentuIzOdeljenja() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getPsoId() {
		return psoId;
	}

	public void setPsoId(Integer psoId) {
		this.psoId = psoId;
	}

	public PredajeOdeljenju getPredajeOdeljenju() {
		return predajeOdeljenju;
	}

	public void setPredajeOdeljenju(PredajeOdeljenju predajeOdeljenju) {
		this.predajeOdeljenju = predajeOdeljenju;
	}

	public Ucenik getUcenik() {
		return ucenik;
	}

	public void setUcenik(Ucenik ucenik) {
		this.ucenik = ucenik;
	}

	public List<Ocena> getOcena() {
		return ocena;
	}

	public void setOcena(List<Ocena> ocena) {
		this.ocena = ocena;
	}
}