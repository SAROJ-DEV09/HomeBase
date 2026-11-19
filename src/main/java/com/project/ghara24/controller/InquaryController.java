package com.project.ghara24.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ghara24.entity.Inquiry;
import com.project.ghara24.entity.Property;
import com.project.ghara24.repository.InquiryRepository;
import com.project.ghara24.repository.PropertyRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class InquaryController {
	
	@Autowired
    private InquiryRepository inquiryRepository;

    @Autowired
    private PropertyRepository propertyRepository;
    
    @PostMapping
    public ResponseEntity<?> submitInquiry(@RequestBody Inquiry inquiry) {
    	Inquiry saved = inquiryRepository.save(inquiry);
		return ResponseEntity.ok(saved);
    }
    
    @GetMapping("/owner/{mobileNumber}")
    public ResponseEntity<?> getInquiriesForOwner(@PathVariable String mobile){
    	
    	List<Property> ownerProperties = propertyRepository.findByMobileNumber(mobile);
    	List<Long> propertyIds = ownerProperties.stream().map(Property::getId).collect(Collectors.toList());
    	
    	if(propertyIds.isEmpty()) {
    		return ResponseEntity.ok(List.of());
    	}
    	
    	List<Inquiry> inquiries = inquiryRepository.findByPropertyIdIn(propertyIds);
        return ResponseEntity.ok(inquiries);
    }
    
    @GetMapping({"/inquiries/owner", "/inquiries/owner/{mobile}"})
    public ResponseEntity<?> getOwnerInquiries(@PathVariable(value = "mobile", required = false) String mobile) {
        if (mobile == null || mobile.trim().isEmpty() || mobile.equals("null")) {
            return ResponseEntity.badRequest().body("Owner mobile number path variable is missing.");
        }
        
        List<Inquiry> inquiries = inquiryRepository.findInquiriesByOwnerMobile(mobile.trim());
        return ResponseEntity.ok(inquiries);
    }
    
}
