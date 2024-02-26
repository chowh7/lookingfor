package com.example.lf.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private LocalDate datePosted;
    private String color;
    private String category;
    private String status;
    private String itemType;
    private ZonedDateTime itemDateTime;
    private String image; // Assume url will be stored
    
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;
    
    public Item() {
    }
    
	public Item(String title, String description, LocalDate datePosted, String color, String category, String status,
			String itemType, ZonedDateTime itemDateTime) {
		this.title = title;
		this.description = description;
		this.datePosted = datePosted;
		this.color = color;
		this.category = category;
		this.status = status;
		this.itemType = itemType;
		this.itemDateTime = itemDateTime;
	}
	
	
	
	public Item(String title, String description, LocalDate datePosted, String color, String category, String status,
			String itemType, ZonedDateTime itemDateTime, String image) {
		this.title = title;
		this.description = description;
		this.datePosted = datePosted;
		this.color = color;
		this.category = category;
		this.status = status;
		this.itemType = itemType;
		this.itemDateTime = itemDateTime;
		this.image = image;
	}
	
	public Item(String title, String description, LocalDate datePosted, String color, String category, String status,
			String itemType, ZonedDateTime itemDateTime, Contact contact, Location location) {
		this.title = title;
		this.description = description;
		this.datePosted = datePosted;
		this.color = color;
		this.category = category;
		this.status = status;
		this.itemType = itemType;
		this.itemDateTime = itemDateTime;
		this.contact = contact;
		this.location = location;
	}
	
	

	public Item(String title, String description, LocalDate datePosted, String color, String category, String status,
			String itemType, ZonedDateTime itemDateTime, String image, Contact contact, Location location) {
		this.title = title;
		this.description = description;
		this.datePosted = datePosted;
		this.color = color;
		this.category = category;
		this.status = status;
		this.itemType = itemType;
		this.itemDateTime = itemDateTime;
		this.image = image;
		this.contact = contact;
		this.location = location;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDatePosted() {
		return datePosted;
	}
	public void setDatePosted(LocalDate datePosted) {
		this.datePosted = datePosted;
	}	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public ZonedDateTime getItemDateTime() {
		return itemDateTime;
	}
	public void setItemDateTime(ZonedDateTime itemDateTime) {
		this.itemDateTime = itemDateTime;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
