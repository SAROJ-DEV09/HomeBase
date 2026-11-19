package com.project.ghara24.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "inquiries")
@Data
public class Inquiry {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Long propertyId;
	    private String customerName;
	    private String customerMobile;
	    private String message;
	    private LocalDateTime inquiryDate = LocalDateTime.now();
	}


