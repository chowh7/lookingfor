package com.example.lf.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item,Long> {
	List<Item> findByItemTypeAndStatus(String itemType, String status);
	
	@Query("SELECT i FROM Item i WHERE i.itemType = :itemType AND i.status = :status AND (i.title LIKE %:keyword% OR i.description LIKE %:keyword%)")
    List<Item> findByKeyword(@Param("itemType") String itemType, 
                              @Param("status") String status, 
                              @Param("keyword") String keyword);
	
}