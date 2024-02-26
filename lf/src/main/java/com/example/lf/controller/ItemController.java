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

import com.example.lf.model.ContactRepository;
import com.example.lf.model.Item;
import com.example.lf.model.ItemRepository;
import com.example.lf.model.LocationRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ItemController {
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private ContactRepository contactRepository;
	
	@PostMapping("/items")
	public ResponseEntity<Item> createItem(@RequestBody Item item){
		try {
			//itemRepository.save(new Item("Wallet", "Blue leather wallet", LocalDate.now(), "Blue", "Personal Acessories", "Active", "Lost", ZonedDateTime.now(), contact, location));
			Item newCourse = new Item(item.getTitle(), item.getDescription(), item.getDatePosted(), item.getColor(), item.getCategory(), item.getStatus(), item.getItemType(), item.getItemDateTime(), item.getContact(), item.getLocation());
			itemRepository.save(newCourse);
			return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/items/batch")
	public ResponseEntity<List<Item>> createItems(@RequestBody List<Item> items){
		try {
			for (Item item : items) {
	            if (item.getLocation() != null && item.getLocation().getId() != null) {
	                item.setLocation(locationRepository.findById(item.getLocation().getId()).orElse(null));
	            }
	            if (item.getContact() != null && item.getContact().getId() != null) {
	                item.setContact(contactRepository.findById(item.getContact().getId()).orElse(null));
	            }
	        }
			List<Item> savedItems = itemRepository.saveAll(items);
			return new ResponseEntity<>(savedItems, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/items/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable("id") long id){
		Optional<Item> itemData = itemRepository.findById(id);
		if (itemData.isPresent()) {
			return new ResponseEntity<>(itemData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/items")
	public ResponseEntity<List<Item>> getAllItems(@RequestParam(required = false) String itemType, String status){
		try {
			List<Item> items = new ArrayList<Item>();
			if (itemType == null) {
				itemRepository.findAll().forEach(items::add);
			} else {
				itemRepository.findByItemTypeAndStatus(itemType, status).forEach(items::add);
			}
			if (items.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(items, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/items/search")
	public ResponseEntity<List<Item>> getItemsByKeyword(@RequestParam(required = true) String itemType, String status, String keyword){
		try {
			List<Item> items = new ArrayList<Item>();
			itemRepository.findByKeyword(itemType, status, keyword).forEach(items::add);
			if (items.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(items, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/items/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable("id") long id, @RequestBody Item item) {
		Optional<Item> itemData = itemRepository.findById(id);

		if (itemData.isPresent()) {
			Item _item = itemData.get();
			_item.setTitle(item.getTitle());
			_item.setDescription(item.getDescription());
			_item.setColor(item.getColor());
			_item.setCategory(item.getCategory());
			_item.setStatus(item.getStatus());
			_item.setItemDateTime(item.getItemDateTime());
			return new ResponseEntity<>(itemRepository.save(_item), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/items/{id}")
	public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") long id) {
		try {
			itemRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
