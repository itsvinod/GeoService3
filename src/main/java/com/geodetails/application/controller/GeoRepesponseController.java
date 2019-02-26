package com.geodetails.application.controller;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import com.geodetails.application.service.GeoRepesponseService;

@RestController
public class GeoRepesponseController {
	Logger logger = LoggerFactory.getLogger(GeoRepesponseController.class);
	@Autowired
	GeoRepesponseService geoRepesponseService;
	
	@Value("${geoapp.errormsg}")
	String errorMsg;
	
	@GetMapping(path = "/geodetails")
	public Callable<String> getGeoDetails(@RequestParam String ip) throws InterruptedException{
		return geoRepesponseService.getGeoDetails(ip);
	}
	
	//ToDo - move to GeoExceptionHandler	
	@ExceptionHandler
	@ResponseBody
	public String handleException(AsyncRequestTimeoutException ex) {
		logger.info("eror msg="+ex.getMessage());
		//ex.printStackTrace();
		return errorMsg;
	}

}