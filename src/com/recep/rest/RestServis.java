package com.recep.rest;

import javax.ws.rs.Path;

import com.google.gson.Gson;
import com.recep.dao.DAO;
import com.recep.entity.Basliklar;
import com.recep.entity.Mesaj;
import com.recep.entity.Rol;
import com.recep.entity.User;
import com.recep.entity.UserKullAdd;
import com.recep.model.BasliklarReturn;
import com.recep.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

@Path("/servis")
public class RestServis {
	Gson gson = new Gson();

	@POST
	@Path("/postDao/")
	public void setDao() {
		DAO.getInstance();
	}

	@POST
	@Path("/postKullList/")
	public String getKullList() {
		String sonuc = "YOK";

		List<User> users = DAO.getInstance().getUserList();

		if (users != null) {
			sonuc = gson.toJson(users);
		} else {
			sonuc = "YOK";
		}

		return sonuc;
	}

	@POST
	@Path("/postGetMesajList/")
	public String getMesajList() {
		String sonuc = "YOK";

		List<Mesaj> mesajs = DAO.getInstance().getMesajList();

		if (mesajs != null) {
			sonuc = gson.toJson(mesajs);
		} else {
			sonuc = "YOK";
		}

		return sonuc;
	}

	@POST
	@Path("/post/")
	public String getKullanici(@FormParam("json") String value) {
		String sonuc = "YOK";

		UserModel userModel = gson.fromJson(value, UserModel.class);

		User user = DAO.getInstance().checkUser(userModel);

		if (user != null) {

			sonuc = gson.toJson(user);

		} else {
			sonuc = "YOK";
		}

		return sonuc;
	}

	@POST
	@Path("/postMesajAdd/")
	public String getMesajAdd(@FormParam("json") String value) {
		String sonuc = "YOK";

		Mesaj mesaj = gson.fromJson(value, Mesaj.class);

		Mesaj getMesaj = (Mesaj) DAO.getInstance().addValue(mesaj);

		if (getMesaj != null) {

			sonuc = gson.toJson(getMesaj);

		} else {
			sonuc = "YOK";
		}

		return sonuc;
	}

	@POST
	@Path("/postKullUpdate/")
	public String getKullUpdate(@FormParam("json") String value) {
		String sonuc = "YOK";

		User user = gson.fromJson(value, User.class);

		User getUser = (User) DAO.getInstance().addUpdate(user);

		UserKullAdd userKullAdd = DAO.getInstance().getUserKullAdd(getUser.getId());

		userKullAdd.setUsername(getUser.getUsername());

		DAO.getInstance().addUpdate(userKullAdd);

		if (getUser != null) {

			sonuc = gson.toJson(getUser);

		} else {
			sonuc = "YOK";
		}

		return sonuc;
	}

	@POST
	@Path("/postsil/")
	public String getKullaniciSil(@FormParam("json") String value) {

		String sonuc = "YOK";

		User user = gson.fromJson(value, User.class);

		// System.out.println(user.getId() + " " + user.getRol().getId() + " " +
		// user.getUserKullAdd().getId()) ;

		DAO.getInstance().addDelete(user);
		// DAO.getInstance().deleteUserId(user.getId());

		User getUser = DAO.getInstance().getUser(user.getId());

		if (getUser != null) {
			sonuc = gson.toJson(getUser);
		} else {
			sonuc = "YOK";
			// DAO.getInstance().deleteUserKullAddId(user.getUserKullAdd().getId());
			// DAO.getInstance().deleteRolId(user.getRol().getId());
			// DAO.getInstance().addDelete(user.getUserKullAdd());
			// DAO.getInstance().addDelete(user.getRol());
		}

		return sonuc;

	}

	@POST
	@Path("/postMesajSil/")
	public String getMesajSil(@FormParam("json") String value) {

		String sonuc = "YOK";

		Mesaj mesaj = gson.fromJson(value, Mesaj.class);

		// System.out.println(mesaj.getId() + " " +mesaj.getMesaj());

		DAO.getInstance().addDelete(mesaj);

		Mesaj getMesaj = DAO.getInstance().getMesaj(mesaj.getId());

		if (getMesaj != null) {
			sonuc = gson.toJson(getMesaj);
		} else {
			sonuc = "YOK";

		}

		return sonuc;

	}

	@POST
	@Path("/postKonuSil/")
	public String getKonuSil(@FormParam("json") String value) {

		String sonuc = "YOK";

		Basliklar basliklar = gson.fromJson(value, Basliklar.class);

		List<Mesaj> mesajList = new ArrayList<>();

		List<Mesaj> mesajs = DAO.getInstance().getMesajList();
		Mesaj mesaj = null;

		for (int i = 0; i < mesajs.size(); i++) {

			if (basliklar.getId() == mesajs.get(i).getBasliklars().getId()) {
				mesaj = mesajs.get(i);
				mesajList.add(mesaj);
			}

		}

		for (int i = 0; i < mesajList.size(); i++) {
			DAO.getInstance().addDelete(mesajList.get(i));
		}

		DAO.getInstance().addDelete(basliklar);

		Basliklar getBasliklar = DAO.getInstance().getBasliklar(basliklar.getId());

		if (getBasliklar != null) {
			sonuc = gson.toJson(getBasliklar);
		} else {
			sonuc = "YOK";

		}

		return sonuc;

	}

	@SuppressWarnings("unused")
	@POST
	@Path("/postKullAdd/")
	public String kullaniciEkle(@FormParam("json") String value) {
		String sonuc = "YOK";

		User user = gson.fromJson(value, User.class);

		// Rol rol = new Rol();
		// rol.setRol_konuIslemleri(getUser.getRol().isRol_konuIslemleri());
		// rol.setRol_kullaniciIslemleri(getUser.getRol().isRol_kullaniciIslemleri());
		//
		// DAO.getInstance().addRol(rol);
		//
		// User user = new User();
		// user.setUsername(getUser.getUsername());
		// user.setPassword(getUser.getPassword());
		// user.setRol(rol);

		DAO.getInstance().addValue(user.getRol());
		DAO.getInstance().addValue(user);

		User getUser = DAO.getInstance().getUser(user.getId());

		UserKullAdd userKullAdd = new UserKullAdd();

		userKullAdd.setId(getUser.getId());
		userKullAdd.setUsername(getUser.getUsername());
		userKullAdd.setRol(getUser.getRol());

		DAO.getInstance().addValue(userKullAdd);

		if (getUser != null) {
			sonuc = gson.toJson(getUser);
		} else {
			sonuc = "YOK";
		}

		return sonuc;
	}

	@POST
	@Path("/postKonuAdd/")
	public String getKonuAdd(@FormParam("json") String value) {
		String sonuc = "YOK";

		Basliklar basliklar = gson.fromJson(value, Basliklar.class);

		Basliklar getBasliklar = (Basliklar) DAO.getInstance().addValue(basliklar);

		if (getBasliklar != null) {

			sonuc = gson.toJson(getBasliklar);

		} else {
			sonuc = "YOK";
		}

		return sonuc;
	}

	@GET
	@Path("/getbasliklar/")
	public String basliklarGetir() {

		String sonuc = null;

		List<Basliklar> basliklar = DAO.getInstance().getBaslilar();

		if (basliklar != null) {

			sonuc = gson.toJson(basliklar);
		}

		return sonuc;
	}

}
