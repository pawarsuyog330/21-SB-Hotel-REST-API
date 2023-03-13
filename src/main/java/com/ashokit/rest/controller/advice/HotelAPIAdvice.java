package com.ashokit.rest.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ashokit.rest.exception.HotelNotFoundException;
import com.ashokit.rest.exception.RoomNotFoundException;

@RestControllerAdvice
public class HotelAPIAdvice {

	@ExceptionHandler(HotelNotFoundException.class)
	public ResponseEntity<?> hotelNotFoundExceptionHandler(HotelNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RoomNotFoundException.class)
	public ResponseEntity<?> roomNotFoundExceptionHandler(RoomNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
