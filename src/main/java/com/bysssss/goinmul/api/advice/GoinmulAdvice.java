package com.bysssss.goinmul.api.advice;

import javax.validation.ConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.bysssss.goinmul.api.exception.GoinmulException;
import com.bysssss.goinmul.api.spec.Response;
import com.bysssss.goinmul.api.spec.ResultCode;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice(annotations=RestController.class)
public class GoinmulAdvice {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> handleException(Exception e) {
		log.error("GoinmulAdvice handleException() : e={}", e);
		Response response = new Response();
		response.setResultCode(ResultCode.INTERNAL_SERVER_ERROR);
		response.setResultMessage(e.getMessage());
		return response.ok();
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Response> handleNullPointerException(NullPointerException e) {
		log.error("GoinmulAdvice handleNullPointerException() : e={}", e);
		Response response = new Response();
		response.setResultCode(ResultCode.INTERNAL_SERVER_ERROR);
		response.setResultMessage(e.getMessage());
		return response.ok();
	}
	
	@ExceptionHandler(BindException.class)
	public ResponseEntity<Response> handleBindException(BindException e) {
		log.error("GoinmulAdvice handleBindException() : e={}", e);
		Response response = new Response();
		response.setResultCode(ResultCode.BAD_REQUEST);
		response.setResultMessage(e.getMessage());
		return response.ok();
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Response> handleConstraintViolationException(ConstraintViolationException e) {
		log.error("GoinmulAdvice handleConstraintViolationException() : e={}", e);
		Response response = new Response();
		response.setResultCode(ResultCode.BAD_REQUEST);
		response.setResultMessage(e.getMessage());
		return response.ok();
	}
	
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<Response> handleInvalidFormatException(InvalidFormatException e) {
		log.error("GoinmulAdvice handleInvalidFormatException() : e={}", e);
		Response response = new Response();
		response.setResultCode(ResultCode.BAD_REQUEST);
		response.setResultMessage(e.getMessage());
		return response.ok();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error("GoinmulAdvice handleMethodArgumentNotValidException() : e={}", e);
		Response response = new Response();
		response.setResultCode(ResultCode.BAD_REQUEST);
		response.setResultMessage(e.getMessage());
		return response.ok();
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Response> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		log.error("GoinmulAdvice handleMethodArgumentTypeMismatchException() : e={}", e);
		Response response = new Response();
		response.setResultCode(ResultCode.BAD_REQUEST);
		response.setResultMessage(e.getMessage());
		return response.ok();
	}
	
	@ExceptionHandler(GoinmulException.class)
	public ResponseEntity<Response> handleGoinmulException(GoinmulException e) {
		//log.error("GoinmulAdvice handleGoinmulException() : e={}", e);
		Response response = e.takeResponse();
		return response.ok();
	}
}
