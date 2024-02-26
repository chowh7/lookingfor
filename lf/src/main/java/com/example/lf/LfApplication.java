package com.example.lf;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.lf.model.Contact;
import com.example.lf.model.ContactRepository;
import com.example.lf.model.Item;
import com.example.lf.model.ItemRepository;
import com.example.lf.model.Location;

@SpringBootApplication
public class LfApplication {
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ContactRepository contactRepository;

	public static void main(String[] args) {
		SpringApplication.run(LfApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init() {
		return args -> {
			Contact contact = new Contact("Wilkins", "Chow", "abc@gmail.com", "12345678");
			Location location = new Location("Douglas College", "11 Eighth Street New Westminster, BC", "Canada", "Campus");			
		    
			itemRepository.save(new Item("Wallet", "Blue leather wallet", LocalDate.now(), "Blue", "Personal Acessories", "Active", "Lost", ZonedDateTime.now(), contact, location));
			itemRepository.save(new Item("USB Flash Drive", "SanDisk 512GB Dual Flash Drive", LocalDate.now(), "Silver", "Electronics", "Active", "Lost", ZonedDateTime.now()));
			itemRepository.save(new Item("Water Bottle", "1L water bottle", LocalDate.now(), "Black", "Sports & Outdoors", "Active", "Found", ZonedDateTime.now()));
			itemRepository.findAll().forEach(System.out::println);

		};
	}

}
