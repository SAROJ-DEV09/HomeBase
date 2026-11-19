package com.project.ghara24.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PropertyResponseDto {
	private String ownerName;
	private String mobileNumber;
	private String propertyAddress;
	private String district;
	private String state;
	private String rentalPurpose;
	private String config;
	private Double rentPrice;
	
	private List<MultipartFile> images;
}
