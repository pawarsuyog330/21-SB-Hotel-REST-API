package com.ashokit.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.rest.constants.AppConstants;
import com.ashokit.rest.model.Hotel;
import com.ashokit.rest.model.Room;
import com.ashokit.rest.service.HotelService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class HotelAPI {

	@Autowired
	HotelService service;
	
	static Logger logger = LoggerFactory.getLogger(HotelAPI.class);

	@GetMapping(value="/hotel/{id}", produces= {"application/json", "application/xml"})
	@ApiOperation(value="endpoint to retrieve a hotel and its romms details")
	public ResponseEntity<Hotel> readHotelDetailsByid(@ApiParam(value="hotel id")@PathVariable Integer id) {
		Hotel hotel = service.getHotelDetailsById(id);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}

	@PostMapping(value="/hotel/add", consumes= {"application/xml"})
	@ApiOperation(value="endpoint to add a new hotel and its rooms details")
	public ResponseEntity<String> addNewHotelDetails(@ApiParam(value ="hotel details")@RequestBody Hotel hotel) {
		logger.info("Hotel Details Captured"+ hotel);
		
		boolean status = service.addHotelDetails(hotel);
		if (status) {
			return new ResponseEntity<String>(AppConstants.ADD_HOTEL_SUCCESS, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(AppConstants.ADD_HOTEL_UNSUCCESS, HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping("/hotel/{h_id}/room/{r_id}")
	@ApiOperation(value="endpint to retrieve room details based on hotel id and room id")
	@ApiResponses({
		@ApiResponse(code=200, message = "response generated successfully"),
		@ApiResponse(code=404, message = "Check the request url")
	})
	public ResponseEntity<Room> readRoomDetailsByHotelAndRoomId(@ApiParam(value="hotel id")@PathVariable Integer h_id, @ApiParam(value="room id")@PathVariable Integer r_id) {
		Room room=service.getRoomDetailsByHotelAndRoomId(h_id, r_id);
		return new ResponseEntity<Room>(room, HttpStatus.OK);
	}
	
	@PutMapping("hotel/update")
	public ResponseEntity<Void> updateHotelDetailsById(@RequestBody Hotel hotel)
	{
		Hotel hotel1=service.updateHotelDetails(hotel);
		if(hotel1 !=null)
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("hotel/room/update")
	public ResponseEntity<Void> updateRoomDetailsById(@RequestBody Room room)
	{
		Room room1=service.updateRoomDetails(room);
		if(room1 !=null)
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@DeleteMapping("hotel/room/delete/{id}")
	public ResponseEntity<Void> removeRoomDetailsById(@PathVariable Integer id)
	{
		boolean flag=service.deleteRoomById(id);
		if(flag)
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("hotel/delete/{id}")
	public ResponseEntity<Void> removeHotelDetailsById(@PathVariable Integer id)
	{
		boolean flag=service.deleteHotelById(id);
		if(flag)
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
}
