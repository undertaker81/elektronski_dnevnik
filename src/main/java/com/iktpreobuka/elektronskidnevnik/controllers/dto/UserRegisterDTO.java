package com.iktpreobuka.elektronskidnevnik.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegisterDTO {

	@JsonProperty("name")
	public String name;
	@JsonProperty("email")
	public String email;

	public UserRegisterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRegisterDTO(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
