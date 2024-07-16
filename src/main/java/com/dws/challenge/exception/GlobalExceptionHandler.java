package com.dws.challenge.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dws.challenge.domain.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	
	@ExceptionHandler(InSufficientBalanceException.class)
	public ResponseEntity<ApiResponse> handleInSufficientBalanceException(InSufficientBalanceException ex){
		String message = ex.getMessage();
		ApiResponse response = new ApiResponse.Builder().setMessage(message).setSuccess(true).setStatus(HttpStatus.BAD_REQUEST).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccountNotExistException.class)
	public ResponseEntity<ApiResponse> handleAccountNotExistException(AccountNotExistException ex){
		String message = ex.getMessage();
		ApiResponse response = new ApiResponse.Builder().setMessage(message).setSuccess(true).setStatus(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler(TransactionFailedException.class)
	public ResponseEntity<ApiResponse> handleTransactionFailedException(Exception ex){
		String message = ex.getMessage();
		ApiResponse response = new ApiResponse.Builder().setMessage(message).setSuccess(true).setStatus(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}



}
