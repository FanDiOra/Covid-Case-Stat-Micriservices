package com.agrosofttechnologies.covidreports.bean;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponseError {

    private Integer code;
    private String message;
    private String description;
    private LocalDateTime dateTime;
    
    public ResponseError() {}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public void prepareInternalServerErrorResponse (String description) {
		prepareResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error", description);
	}
	
	public void prepareBadRequestResponse (String description) {
		prepareResponseError(HttpStatus.BAD_REQUEST.value(), "Bad request", description);
	}
	
	public void prepareNotFoundResponse (String description) {
		prepareResponseError(HttpStatus.NOT_FOUND.value(), "Not found", description);
	}

	private void prepareResponseError (Integer code, String message, String description) {
		this.setCode(code);
		this.setMessage(message);
		this.setDescription(description);
		this.setDateTime(LocalDateTime.now());
	}
}
