package com.example.lf.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {
	List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
	List<Contact> findByEmailAndPhone(String email, String phone);
	List<Contact> findByFirstName(String firstName);
	List<Contact> findByLastName(String lastName);
	List<Contact> findByPhone(String phone);
	List<Contact> findByEmail(String email);
	
}
