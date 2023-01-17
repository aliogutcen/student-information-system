package com.ogutcenali.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import com.ogutcenali.dao.StudentDao;
import com.ogutcenali.entity.Contact;
import com.ogutcenali.entity.Student;

public class StudentService {

	private StudentDao studentDao = new StudentDao();

	public void makeStudent(String fname, String lname, String email, String gender, Contact contact, byte[] data) throws FileNotFoundException {
			if(data ==null) {
			
			FileInputStream fis = new FileInputStream("C:/Users/PC/Desktop/logo/images.png");
			try {
				data = new byte[fis.available()];
				fis.read(data);
				Student student = new Student(fname, lname, email, gender, contact,data);
				studentDao.save(student);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
	
		else {
			Student student = new Student(fname, lname, email, gender, contact,data);
			studentDao.save(student);
		}
		
	}

	public List<Student> getAll() {

		return studentDao.listAll();
	}

	public void deleteStudent(long id) {

		studentDao.delete(id);

	}

	public Student findStudentInfo(long id) {

		Student student = studentDao.findById(id);

		return student;
	}

	public List<Student> findByEmail(String email) {

		List<Student> studentList = studentDao.listAll();

		List<Student> students = studentList.stream().filter((s) -> s.getEmail().contains(email)).toList();
		return students;
				}

		

	public List<Student> withFirstName(String name) {

		List<Student> withFirstName = studentDao.listAll().stream()
				.filter((s) -> s.getFirstName().startsWith(name))
				.toList();

		return withFirstName;
	}

	public List<Student> withLastName(String lastName) {
		
		List<Student> withLastName = studentDao.listAll().stream()
				.filter((s)-> s.getLastName().startsWith(lastName))
				.toList();
		
		
		
		return withLastName;
	}

	public void updateStudent(long id, String fname, String lname, String email, String gender, Contact contact) {
		
		Student student = studentDao.findById(id);
		student.setFirstName(fname);
		student.setLastName(lname);
		student.setEmail(email);
		student.setGender(gender);
		student.setContact(contact);
		
		
		studentDao.update(student);
	}
	
	

}
