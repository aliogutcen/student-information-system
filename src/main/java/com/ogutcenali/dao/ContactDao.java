package com.ogutcenali.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ogutcenali.entity.Contact;
import com.ogutcenali.entity.Student;
import com.ogutcenali.utils.HibernateUtils;

public class ContactDao  {

	private Session session;
	private Transaction transaction;

	private void openTransaction() {

	session = HibernateUtils.getSessionFactory().openSession();
		transaction = session.beginTransaction();

	}

	private void accessTransaction() {
		transaction.commit();
		session.close();
	}

	private void errorTransaction() {
		if (transaction == null) {
			transaction.rollback();
		}

	}

	public Contact save(Contact contact) {
		try {
			openTransaction();
			session.save(contact);
			accessTransaction();
		}catch (Exception e) {
			errorTransaction();
		}
		return contact;
	}

	public Contact findById(long id) {

		session = HibernateUtils.getSessionFactory().openSession();

		Contact contact;

		try {
			contact = session.find(Contact.class, id);
			if (contact != null) {
				return contact;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			session.close();
		}

		return null;
	}
	
public void update(Contact t) {
		
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			
			session.merge(t);
			transaction.commit();
		}catch (Exception e) {
			System.out.println("Update ogrencide patladık");
			if (transaction == null) {
				transaction.rollback();
			}
		}

	}

public void delete(long id) {
	try {
		Contact contact = findById(id);
		if (contact != null) {
			openTransaction();
			session.delete(contact);
			accessTransaction();
		}
	} catch (Exception e) {
		System.out.println(e.getMessage());
		errorTransaction();
	}

	
}


}
