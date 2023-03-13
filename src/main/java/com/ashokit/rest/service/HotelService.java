package com.ashokit.rest.service;

import com.ashokit.rest.model.Hotel;
import com.ashokit.rest.model.Room;

public interface HotelService {

	public Hotel getHotelDetailsById(Integer id);

	public boolean addHotelDetails(Hotel hotel);

	public Room getRoomDetailsByHotelAndRoomId(Integer hotelId, Integer roomId);

	public Hotel updateHotelDetails(Hotel hotel);

	public Room updateRoomDetails(Room room);

	public boolean deleteRoomById(Integer id);

	public boolean deleteHotelById(Integer id);
}
