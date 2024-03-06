package com.example.lf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lf.model.Contact;
import com.example.lf.model.ContactRepository;
import com.example.lf.model.Item;
import com.example.lf.model.ItemRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ContactController {
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ContactRepository contactRepository;
	
	@PostMapping("/contacts")
	public ResponseEntity<Contact> createContact(@RequestBody Contact contact){
		try {
			List <Contact> dbContact = contactRepository.findByEmailAndPhone(contact.getEmail(), contact.getPhone());
			if(dbContact.isEmpty()) {
				Contact _contact = contactRepository.save(new Contact(contact.getFirstName(),contact.getLastName()
						,contact.getEmail(),contact.getPhone()));
				return new ResponseEntity<>(_contact, HttpStatus.CREATED);
				}
			else {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@GetMapping("/contacts/{id}")
//    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
//		Optional<Item> itemData = itemRepository.findById(id);
//		if (itemData.isPresent()) {
//			return new ResponseEntity<>(itemData.get().getContact(), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//    }

	@GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
		Optional<Contact> itemData = contactRepository.findById(id);
		if (itemData.isPresent()) {
			return new ResponseEntity<>(itemData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }

	
	@GetMapping("/contacts")
	public ResponseEntity<List<Contact>> getAllContacts(
								@RequestParam(required = false)String firstName, 
								@RequestParam(required = false) String lastName,
								@RequestParam(required = false) String email,
						        @RequestParam(required = false) String phone){
		try {
			List<Contact> contacts = new ArrayList<>();
			if (firstName == null && lastName == null && email == null && phone == null) {
				contactRepository.findAll().forEach(contacts::add);
			} else if (firstName != null && lastName != null) {
				contactRepository.findByFirstNameAndLastName(firstName, lastName).forEach(contacts::add);	
			} else {
	            // Handle cases where only one parameter is provided
	            if (firstName != null) {
	                contactRepository.findByFirstName(firstName).forEach(contacts::add);
	            } else if (lastName != null){
	                contactRepository.findByLastName(lastName).forEach(contacts::add);
	            }else if (email != null){
	                contactRepository.findByEmail(email).forEach(contacts::add);
	            }else if (phone != null){
	                contactRepository.findByPhone(phone).forEach(contacts::add);
	            }
	            
	        }
			if(contacts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(contacts, HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@PutMapping("/contacts/{id}")
	public ResponseEntity<Contact> updateContact(@PathVariable("id") long id, @RequestBody Contact item) {
		Optional<Contact> contactData = contactRepository.findById(id);

		if (contactData.isPresent()) {
			Contact _item = contactData.get();
			_item.setFirstName(item.getFirstName());
			_item.setLastName(item.getLastName());
			_item.setEmail(item.getEmail());
			_item.setPhone(item.getPhone());

			return new ResponseEntity<>(contactRepository.save(_item), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/contacts/{id}")
	public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") long id) {
		try {
			contactRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
