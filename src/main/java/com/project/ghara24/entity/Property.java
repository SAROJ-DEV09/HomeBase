package com.project.ghara24.entity;
import jakarta.persistence.*;

import java.util.List;
import lombok.Data;

@Entity
@Table(name = "properties")
@Data
public class Property {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String ownerName;
	    private String mobileNumber;
	    private String address;
	    private String district;
	    private String state = "Odisha";
	    private String purpose;
	    private String config;
	    private Double price;

	    @ElementCollection
	    @CollectionTable(name = "property_images", joinColumns = @JoinColumn(name = "property_id"))
	    @Column(name = "image_url", length = 1000)
	    private List<String> imageUrls;
}

