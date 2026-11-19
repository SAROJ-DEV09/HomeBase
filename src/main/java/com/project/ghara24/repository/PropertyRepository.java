package com.project.ghara24.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ghara24.entity.Property;

public interface PropertyRepository extends JpaRepository<Property,Long> {
	List<Property> findByMobileNumber(String mobileNuber);

}
