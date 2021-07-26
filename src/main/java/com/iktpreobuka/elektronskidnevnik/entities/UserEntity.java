package com.iktpreobuka.elektronskidnevnik.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer id;
	@Column(name = "name")
	@NotNull(message = "Ime mora biti uneseno.")
	@Size(min=2, max=30, message = "Ime mora biti izmedju {min} i {max} karaktera.")
	private String name;
	@Column(name = "lastName")
	@NotNull(message = "Prezime mora biti uneseno.")
	@Size(min=2, max=30, message = "Prezime mora biti izmedju {min} i {max} karaktera.")
	private String lastName;
	@Column(name = "username")
	@NotNull(message = "Username mora biti unesen.")
	@Size(min=3, max=15, message = "Username mora biti izmedju {min} i {max} karaktera.")
	private String username;
	@Column(name = "password")
	@NotNull(message = "Lozinka mora biti unesena.")
	@Size(min=5, max=60, message = "Lozinka mora biti izmedju {min} i {max} karaktera.")
	private String password;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "role")
	private RoleEntity role;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public RoleEntity getRole() {
		return role;
	}
	public void setRole(RoleEntity role) {
		this.role = role;
	}
	public UserEntity(Integer id, String name, String lastName, String username, String password, RoleEntity role) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
