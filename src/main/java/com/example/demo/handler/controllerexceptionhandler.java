package com.example.demo.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.excption.CustomException;
import com.example.demo.handler.excption.CustomValidationException;
import com.example.demo.util.Script;



@RestController
@ControllerAdvice // 모든 exception을 다낚아챔 응답은 data를 응답할거라 ..restcontroller
public class controllerexceptionhandler {

	// CMrespDto,Script 비교
	// 1.클라이언트에게 응답할 때는 Script좋음.
	// 2.Ajax 통신-CMrespDto쓰게됨 ->개발자가 응답받을때
	// Android xxhdtls CMREspdDto->개발자가응답받을때
	@ExceptionHandler(CustomValidationException.class)
	public String/* CMrespDTO<?> */ validationException(CustomValidationException e) {
		System.out.println(e.getErrorMap());
		if (e.getErrorMap() == null) {
			return Script.back(e.getMessage());
		}

		else {
			return Script.back(e.getErrorMap().toString());
		}
		
		
		
		
		
	}
	
	
	@ExceptionHandler(CustomException.class)
	public String/* CMrespDTO<?>*/ CustomExceptions(CustomException e) {
		return Script.back(e.getMessage());
	}
		
}
