package com.iktpreobuka.elektronskidnevnik.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ucenik extends UserEntity { // StudentEntity

	@Column(name = "prosecnaOcena")
	private Float prosecnaOcena;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn
	private Roditelj roditelj;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "roditelj")
	private PredajeOdeljenju predajeOdeljenju;

	public Float getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(Float prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public Roditelj getRoditelj() {
		return roditelj;
	}

	public void setRoditelj(Roditelj roditelj) {
		this.roditelj = roditelj;
	}

	public PredajeOdeljenju getPredajeOdeljenju() {
		return predajeOdeljenju;
	}

	public void setPredajeOdeljenju(PredajeOdeljenju predajeOdeljenju) {
		this.predajeOdeljenju = predajeOdeljenju;
	}

	public Ucenik(Integer id, String name, String lastName, String username, String password, RoleEntity role,
			Float prosecnaOcena, Roditelj roditelj, PredajeOdeljenju predajeOdeljenju) {
		super(id, name, lastName, username, password, role);
		this.prosecnaOcena = prosecnaOcena;
		this.roditelj = roditelj;
		this.predajeOdeljenju = predajeOdeljenju;
	}

	public Ucenik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ucenik(Integer id, String name, String lastName, String username, String password, RoleEntity role) {
		super(id, name, lastName, username, password, role);
		// TODO Auto-generated constructor stub
	}

}
