package com.recep.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.recep.entity.Basliklar;
import com.recep.entity.Mesaj;
import com.recep.entity.Rol;
import com.recep.entity.User;
import com.recep.entity.UserKullAdd;
import com.recep.model.BasliklarReturn;
import com.recep.model.UserModel;

public class DAO {

	private static DAO uniqueInstance;

	public static DAO getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DAO();
		}
		return uniqueInstance;
	}

	SessionFactory sessionFactory;

	public DAO() {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	public User checkUser(UserModel userModel) {
		User user = null, userTest = null;
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);

		// System.out.println(userModel.getKullaniciAdi() + " //// " +
		// userModel.getParola());

		criteria.add(Restrictions.eq("username", userModel.getKullaniciAdi()));
		criteria.add(Restrictions.eq("password", userModel.getParola()));
		List myList = criteria.list();
		for (int i = 0; i < myList.size(); i++) {
			userTest = (User) myList.get(i);
			if (userTest.getUsername().equals(userModel.getKullaniciAdi())
					&& userTest.getPassword().equals(userModel.getParola())) {
				user = (User) myList.get(i);
			}
		}
 
		session.close();
		return user;
	}

	public List<Basliklar> getBaslilar() {
		//List<BasliklarReturn> basliklarReturns = null;
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Basliklar.class);

		List<Basliklar> myList = criteria.list();

//		if (myList != null) {
//
//			basliklarReturns = new ArrayList<>();
//
//			for (int i = 0; i < myList.size(); i++) {
//				basliklarReturns.add(new BasliklarReturn(myList.get(i).getId(), myList.get(i).getBaslik(),
//						myList.get(i).getUser().getUsername()));
//			}
//		}

		session.close();

		return myList;
	}

	public Object addValue(Object object) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(object);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return object;
	}

	public Object addDelete(Object object) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(object);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return object;
	}
	
	public Object addUpdate(Object object) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.merge(object);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return object;
	}
	
	public boolean deleteUserId(Serializable id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	    User persistentInstance = session.load(User.class, id);
	    if (persistentInstance != null) {
	    	tx = session.beginTransaction();
	        session.delete(persistentInstance);
	        tx.commit();
	        session.close();
	        return true;
	    }
	    return false;
	}
	
	public boolean deleteRolId(Serializable id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Rol persistentInstance = session.load(Rol.class, id);
	    if (persistentInstance != null) {
	    	tx = session.beginTransaction();
	        session.delete(persistentInstance);
	        tx.commit();
	        session.close();
	        return true;
	    }
	    return false;
	}
	
	public boolean deleteUserKullAddId(Serializable id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		UserKullAdd persistentInstance = session.load(UserKullAdd.class, id);
	    if (persistentInstance != null) {
	    	tx = session.beginTransaction();
	        session.delete(persistentInstance);
	        tx.commit();
	        session.close();
	        return true;
	    }
	    return false;
	}

	public User getUser(int id) {
		User user = null;
		List<User> myList = null;
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);

		criteria.add(Restrictions.eq("id", id));

		myList = criteria.list();

		if (myList != null && myList.size() > 0) {
			user = myList.get(0);
		}

		session.close();
		return user;
	}

	public List<User> getUserList() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);

		List<User> myList = criteria.list();

		session.close();
		return myList;
	}

	public List<Mesaj> getMesajList() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Mesaj.class);

		List<Mesaj> myList = criteria.list();

		session.close();
		return myList;
	}

	public Mesaj getMesaj(int id) {
		Mesaj mesaj = null;
		List<Mesaj> myList = null;
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Mesaj.class);

		criteria.add(Restrictions.eq("id", id));

		myList = criteria.list();

		if (myList != null && myList.size() > 0) {
			mesaj = myList.get(0);
		}

		session.close();
		return mesaj;
	}
	
	public UserKullAdd getUserKullAdd(int id) {
		UserKullAdd userKullAdd = null;
		List<UserKullAdd> myList = null;
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(UserKullAdd.class);

		criteria.add(Restrictions.eq("id", id));

		myList = criteria.list();

		if (myList != null && myList.size() > 0) {
			userKullAdd = myList.get(0);
		}

		session.close();
		return userKullAdd;
	}

	public Basliklar getBasliklar(int id) {
		Basliklar basliklar = null;
		List<Basliklar> myList = null;
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Basliklar.class);

		criteria.add(Restrictions.eq("id", id));

		myList = criteria.list();

		if (myList != null && myList.size() > 0) {
			basliklar = myList.get(0);
		}

		session.close();
		return basliklar;
	}

}
