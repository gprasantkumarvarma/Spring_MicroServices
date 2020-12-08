package com.demo.microservice.client.getcustomerInfo.restcontroler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

	//add an exception handler for CustomerNotFountException
	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFountException exp){
		
		//create CustomerErrorResponse
		CustomerErrorResponse error = new CustomerErrorResponse(
				HttpStatus.NOT_FOUND.value(),exp.getMessage(),System.currentTimeMillis());
		
		
		
		//return ResponseEntity
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	//add another exception handler for all
	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exp){
		
		//create CustomerErrorResponse
		CustomerErrorResponse error = new CustomerErrorResponse(
				HttpStatus.BAD_REQUEST.value(), exp.getMessage(), System.currentTimeMillis());
		
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
}
