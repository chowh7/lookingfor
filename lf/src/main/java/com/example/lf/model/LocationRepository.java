package com.example.lf.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository <Location,Long>{
	List<Location> findByNameAndAddressAndCountry(String name, String address, String country);

	List<Location> findByName(String name);
	List<Location> findByAddress(String address);
	List<Location> findByCountry(String country);
}
