package com.iktpreobuka.elektronskidnevnik.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Nastavnik extends UserEntity { 

	@NotNull(message = "Email ne moze biti prazan.")
	@Column(name = "email")
	private String email;
	@JoinColumn
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Odeljenje odeljenje;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn
	private Predaje predaje;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Odeljenje getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(Odeljenje odeljenje) {
		this.odeljenje = odeljenje;
	}

	public Predaje getPredaje() {
		return predaje;
	}

	public void setPredaje(Predaje predaje) {
		this.predaje = predaje;
	}

	public Nastavnik(Integer id, String name, String lastName, String username, String password, RoleEntity role,
			@NotNull(message = "Email ne moze biti prazan.") String email, Odeljenje odeljenje, Predaje predaje) {
		super(id, name, lastName, username, password, role);
		this.email = email;
		this.odeljenje = odeljenje;
		this.predaje = predaje;
	}

	public Nastavnik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Nastavnik(Integer id, String name, String lastName, String username, String password, RoleEntity role) {
		super(id, name, lastName, username, password, role);
		// TODO Auto-generated constructor stub
	}

}