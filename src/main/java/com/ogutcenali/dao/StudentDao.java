package com.ogutcenali.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ogutcenali.entity.Student;
import com.ogutcenali.utils.HibernateUtils;

public class StudentDao implements ICrud<Student> {

	private Session session;
	private Transaction transaction;

	private void openTransaction() {

		session = dataBaseConnectionHibernate();
		transaction = session.beginTransaction();

	}

	private void accessTransaction() {
		transaction.commit();
		session.close();
	}

	private void errorTransaction() {
		if (transaction != null) {
			transaction.rollback();
		}

	}

	@Override
	public void save(Student t) {
		try {
			openTransaction();
			session.save(t);
			accessTransaction();
		} catch (Exception e) {
			errorTransaction();
		}
	}

	@Override
	public void update(Student t) {
		
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

	@Override
	public void delete(long id) {
		try {
			Student student = findById(id);
			if (student != null) {
				openTransaction();
				session.delete(student);
				accessTransaction();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			errorTransaction();
		}
	}

	@Override
	public Student findById(long id) {

		session = dataBaseConnectionHibernate();

		Student student;

		try {
			student = session.find(Student.class, id);
			if (student != null) {
				return student;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			session.close();
		}

		return null;
	}

	@Override
	public List<Student> listAll() {
		Session session = dataBaseConnectionHibernate();
		TypedQuery<Student> userQuery = session.createQuery("from Student", Student.class);
		List<Student> student = userQuery.getResultList();
		return student;

	}
	
	
	
	
	
	

}
