package com.recep.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.recep.entity.Rol;

@Entity
public class UserKullAdd {

	@Id
	private int id;

	private String username;

	@OneToOne
	private Rol rol;

	public UserKullAdd(){
		rol = new Rol();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}
