package com.asint.BoxOffice.Movies.Exceptions;

import java.time.LocalDateTime;

public class ErrorDetails {

	private LocalDateTime dateAndTime;
	
	private String message;
	
	private String description;
	  
	  
	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}
	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
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
	
}
