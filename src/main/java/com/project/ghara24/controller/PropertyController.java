package com.project.ghara24.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.ghara24.dto.PropertyRequestDto;
import com.project.ghara24.entity.Property;
import com.project.ghara24.service.PropertyService;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "*")
public class PropertyController {
		
	@Autowired
    private PropertyService propertyService;
	
	@GetMapping
	public ResponseEntity<List<Property>> getAllProperties(){
		return ResponseEntity.ok(propertyService.getAllProperties());
	}
	
	@GetMapping("/owner/{mobileNumber}")
	public ResponseEntity<List<Property>> getPropertiesByOwnerMobileNumber(@PathVariable String mobile){
		return ResponseEntity.ok(propertyService.getPropertiesByOwnerNumber(mobile));		
	}
	
	@PostMapping
    public ResponseEntity<?> createProperty(
            @RequestParam("ownerName") String ownerName,
            @RequestParam("mobileNumber") String mobileNumber,
            @RequestParam("address") String address,
            @RequestParam("district") String district,
            @RequestParam("purpose") String purpose,
            @RequestParam("config") String config,
            @RequestParam("price") Double price,
            @RequestParam(value = "images", required = false) MultipartFile[] images
    ) {
        try {
            PropertyRequestDto dto = new PropertyRequestDto();
            dto.setOwnerName(ownerName);
            dto.setMobileNumber(mobileNumber);
            dto.setPropertyAddress(address);
            dto.setDistrict(district);
            dto.setRentalPurpose(purpose);
            dto.setConfig(config);
            dto.setRentPrice(price);
            dto.setImages(images != null ? Arrays.asList(images) : null);

            Property savedProperty = propertyService.saveProperty(dto);
            return ResponseEntity.ok(savedProperty);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to upload images: " + e.getMessage());
        }
    }
	
}
