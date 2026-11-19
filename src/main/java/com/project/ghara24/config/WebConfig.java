package com.project.ghara24.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	
	//Get absolute path of the uploads folder
	Path uploadDir = Paths.get("uploads");
	String uploadPath = uploadDir.toFile().getAbsolutePath();
	
	//Expose /Upload/** URLs to map to the local upload directory
	registry.addResourceHandler("uploads/**")
					.addResourceLocations("file:" + uploadPath+ "/");
		}
}