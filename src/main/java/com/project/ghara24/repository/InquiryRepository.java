package com.project.ghara24.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.ghara24.entity.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry,Long>{
	List<Inquiry> findByPropertyIdIn(List<Long> propertyIds);
		
	@Query("SELECT i FROM Inquiry i JOIN Property p ON i.propertyId = p.id WHERE p.mobileNumber = :mobileNumber")
	List<Inquiry> findInquiriesByOwnerMobile(@Param("mobileNumber") String mobileNumber);
}
