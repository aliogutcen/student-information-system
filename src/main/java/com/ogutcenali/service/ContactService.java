package com.ogutcenali.service;

import com.ogutcenali.dao.ContactDao;
import com.ogutcenali.entity.Contact;

public class ContactService {

	private ContactDao contactDao = new ContactDao();
	
	public Contact makeContact(String phone, String addres, String phone1, String addres1) {
		
		Contact contact = new Contact(phone, addres, phone1, addres1);

		return contactDao.save(contact);
	}

	public Contact updateContact(long id, String phone1, String adres1, String phone2, String adres2) {
		
		
		Contact contact = contactDao.findById(id);
		contact.setAddres(adres1);
		contact.setAddress1(adres2);
		contact.setPhone(phone1);
		contact.setPhone1(phone2);
		
		contactDao.update(contact);
		
		
		return contact;
	}

	public void deleteContact(long id) {
		contactDao.delete(id);
		
	}

}
