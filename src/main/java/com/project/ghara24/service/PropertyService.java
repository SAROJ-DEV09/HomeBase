package com.project.ghara24.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ghara24.dto.PropertyRequestDto;
import com.project.ghara24.entity.Property;
import com.project.ghara24.mapper.PropertyMapper;
import com.project.ghara24.repository.PropertyRepository;

@Service
public class PropertyService {
	
	@Autowired
	private PropertyRepository propertyRepository;
	
	@Autowired
	private PropertyMapper propertyMapper;
	
	private final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";
	
	public List<Property> getAllProperties(){
		return propertyRepository.findAll();
	}
	
	public List<Property> getPropertiesByOwnerNumber(String mobNumber){
		return propertyRepository.findByMobileNumber(mobNumber);
	}
	
	public Property saveProperty(PropertyRequestDto dto) throws IOException {
		File uploadDir = new File(UPLOAD_DIR);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		List<String> imageUrls = new ArrayList<>();
        if (dto.getImages() != null) {
            for (MultipartFile image : dto.getImages()) {
                if (!image.isEmpty()) {
                    String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
                    Path filePath = Paths.get(UPLOAD_DIR + fileName);
                    Files.write(filePath, image.getBytes());
                    imageUrls.add("/uploads/" + fileName);
                }
            }
        }
        
        Property property = propertyMapper.toEntity(dto, imageUrls);
        return propertyRepository.save(property);
	}
	
	

}
