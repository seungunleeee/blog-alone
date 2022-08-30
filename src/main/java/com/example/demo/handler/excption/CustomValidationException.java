package com.example.demo.handler.excption;

import java.util.Map;

import net.bytebuddy.implementation.bind.annotation.Super;
 
public class CustomValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private Map<String, String> errorMap ;
	public CustomValidationException(String message,Map<String, String> errorMap) {
		super(message);
		this.errorMap=errorMap;
	
		
	}
	public Map<String, String> getErrorMap() {                                                                                                                                            
		return errorMap;
	}


}
