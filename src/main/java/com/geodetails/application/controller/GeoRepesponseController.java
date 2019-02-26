package com.geodetails.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.geodetails.application.service.GeoRepesponseService;

@RestController
public class GeoRepesponseController {
	Logger logger = LoggerFactory.getLogger(GeoRepesponseController.class);
	@Autowired
	GeoRepesponseService geoRepesponseService;
	@GetMapping(path = "/geodetails")
	public String getGeoDetails() {
		return geoRepesponseService.getGeoDetails();
	}
}