package com.dws.challenge.domain;


import org.springframework.http.HttpStatus;

public class ApiResponse {
	
	private String message;
	private boolean success;
	private HttpStatus status;
	
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
public static class Builder{
	private final ApiResponse apiResponse;
	
	public Builder() {
		apiResponse = new ApiResponse();
	}
	
	public Builder setMessage(String msg) {
		apiResponse.setMessage(msg);
		return this;
	}
	public Builder setSuccess(boolean success) {
		apiResponse.setSuccess(success);
		return this;
	}
	public Builder setStatus(HttpStatus status) {
		apiResponse.setStatus(status);
		return this;
	}
	
	public ApiResponse build() {
		return apiResponse;
	}
		
	}

}
