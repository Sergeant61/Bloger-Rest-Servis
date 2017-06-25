package com.recep.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Mesaj {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String mesaj;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Basliklar basliklars;
	
	@OneToOne
	private User user;
	
	private Date tarih;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMesaj() {
		return mesaj;
	}

	public void setMesaj(String mesaj) {
		this.mesaj = mesaj;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Basliklar getBasliklars() {
		return basliklars;
	}

	public void setBasliklars(Basliklar basliklars) {
		this.basliklars = basliklars;
	}

	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}
	
	

}
