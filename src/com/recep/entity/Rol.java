package com.recep.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private boolean rol_kullaniciIslemleri;

	private boolean rol_konuIslemleri;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isRol_kullaniciIslemleri() {
		return rol_kullaniciIslemleri;
	}

	public void setRol_kullaniciIslemleri(boolean rol_kullaniciIslemleri) {
		this.rol_kullaniciIslemleri = rol_kullaniciIslemleri;
	}

	public boolean isRol_konuIslemleri() {
		return rol_konuIslemleri;
	}

	public void setRol_konuIslemleri(boolean rol_konuIslemleri) {
		this.rol_konuIslemleri = rol_konuIslemleri;
	}

}
