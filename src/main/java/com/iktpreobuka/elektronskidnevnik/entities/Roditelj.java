package com.iktpreobuka.elektronskidnevnik.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Roditelj extends UserEntity { // ParentEntity

	@Column(name = "email")
	private String email;
	@JsonIgnore
	@OneToMany(mappedBy = "roditelj", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<Ucenik> listaUcenika;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Ucenik> getListaUcenika() {
		return listaUcenika;
	}

	public void setListaUcenika(List<Ucenik> listaUcenika) {
		this.listaUcenika = listaUcenika;
	}

	public Roditelj(Integer id, String name, String lastName, String username, String password, RoleEntity role,
			String email, List<Ucenik> listaUcenika) {
		super(id, name, lastName, username, password, role);
		this.email = email;
		this.listaUcenika = listaUcenika;
	}

	public Roditelj() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Roditelj(Integer id, String name, String lastName, String username, String password, RoleEntity role) {
		super(id, name, lastName, username, password, role);
		// TODO Auto-generated constructor stub
	}

}
