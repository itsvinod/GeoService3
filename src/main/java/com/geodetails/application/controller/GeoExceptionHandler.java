package com.geodetails.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//java.lang.InterrputedException
@SuppressWarnings({ "unchecked", "rawtypes" })
//@ControllerAdvice
public class GeoExceptionHandler extends ResponseEntityExceptionHandler {
	public final static String ERROR_MSG = "due to high traffic we are not able to handle the request. Please try again later";

	//@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		// log actual exception - ex.getLocalizedMessage()
		details.add(ERROR_MSG);
		ErrorResponse error = new ErrorResponse("Server Error", details);
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}