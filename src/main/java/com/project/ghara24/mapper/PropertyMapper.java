package com.project.ghara24.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.project.ghara24.dto.PropertyRequestDto;
import com.project.ghara24.entity.Property;

@Component
public class PropertyMapper {
	public Property toEntity(PropertyRequestDto request,List<String> imageUrl) {	
		if(request==null) { 
		return null;
		
		}
		
		Property property = new Property();
		property.setOwnerName(request.getOwnerName());
		property.setMobileNumber(request.getMobileNumber());
		property.setAddress(request.getPropertyAddress());
		property.setDistrict(request.getDistrict());
		property.setState(request.getState());
		property.setPurpose(request.getRentalPurpose());
		property.setConfig(request.getConfig());
		property.setPrice(request.getRentPrice());
		property.setImageUrls(imageUrl);
				
		return property;
	}
	
//	public static PropertyResponseDto toResponse(Property property) {
//		return new PropertyResponseDto(property.getOwnerName(),property.getMobileNumber(),property.getAddress(),property.getDistrict(),
//				property.getState(),property.getPurpose(),property.getConfig(),property.getPrice(),property.getImageUrls());
//		
//	}
}
