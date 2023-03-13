package com.ashokit.rest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ashokit.rest.entity.HotelEntity;
import com.ashokit.rest.entity.RoomEntity;
import com.ashokit.rest.exception.HotelNotFoundException;
import com.ashokit.rest.exception.RoomNotFoundException;
import com.ashokit.rest.model.Hotel;
import com.ashokit.rest.model.Room;
import com.ashokit.rest.repository.HotelRepository;
import com.ashokit.rest.repository.RoomRepository;
import com.ashokit.rest.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	HotelRepository hotelRepo;
	
	static Logger logger=LoggerFactory.getLogger(HotelServiceImpl.class);

	@Autowired
	RoomRepository roomsRepo;

	@Cacheable(value="hotelDetails", key="#id", unless="#result == null")
	@Override
	public Hotel getHotelDetailsById(Integer id) {
		Optional<HotelEntity> opt = hotelRepo.findById(id);
		Hotel hotel = new Hotel();
		if (opt.isPresent()) {
			HotelEntity hotelEntity = opt.get();
			BeanUtils.copyProperties(hotelEntity, hotel);
			
			List<RoomEntity> rE=hotelEntity.getRooms();
			List<Room> lstRooms=new ArrayList<>();
			rE.forEach(r->
			{
				Room room=new Room();
				BeanUtils.copyProperties(r, room);
				lstRooms.add(room);
			});
			hotel.setRooms(lstRooms);
			
		} else {
			throw new HotelNotFoundException("Hotel with this id " + id + " is not found");
		}
		return hotel;
	}

	@Override
	public boolean addHotelDetails(Hotel hotel) {
		HotelEntity hotelEntity=new HotelEntity();
		BeanUtils.copyProperties(hotel, hotelEntity);
		
		List<Room> lstfromHotel=hotel.getRooms();
		List<RoomEntity> lstForHotelEntity=new ArrayList<>();
		
		lstfromHotel.forEach(r->
		{
			RoomEntity re=new RoomEntity();
			BeanUtils.copyProperties(r, re);
			lstForHotelEntity.add(re);
		});
		
		hotelEntity.setRooms(lstForHotelEntity);
		
		logger.info("Hotel Entity Copied " + hotelEntity);
		if(!hotelRepo.existsById(hotelEntity.getId()))
		{
			hotelRepo.save(hotelEntity);
			return true;
		}
		
		return false;
	}

	@Override
	@Cacheable(value="roomDetails", key="#hotelId-#roomId", unless="#result == null")
	public Room getRoomDetailsByHotelAndRoomId(Integer hotelId, Integer roomId) {
		Optional<RoomEntity> opt=roomsRepo.findRoomByHotelAndRoomId(hotelId, roomId);
		Room room =new Room();
		if(opt.isPresent())
		{
			RoomEntity roomEntity=opt.get();
			BeanUtils.copyProperties(roomEntity, room);
		}
		else
		{
			throw new RoomNotFoundException(String.format("Room with id {} in Hotel with id {} does not exist", roomId, hotelId));
		}
		return room;
	}

	@Override
	@CachePut(value="HOTELCache", key="#hotel.id", unless="#result == null")
	public Hotel updateHotelDetails(Hotel hotel) {
		if(hotelRepo.existsById(hotel.getId()))
		{
			hotelRepo.updateHotelDetailsById(hotel.getName(), hotel.getDescription(), hotel.getZipcode(), hotel.isActive(), hotel.getId());
			return hotel;
		}
		return null;
	}

	@Override
	public Room updateRoomDetails(Room room) {
		if(roomsRepo.existsById(room.getId()))
		{
			roomsRepo.updateHotelRoomDetailsById(room.getDisplayName(), room.getQuantity(), room.getPrice(), room.getId());
			return room;
		}
		return null;
	}

	@Override
	@CacheEvict(value = "hotelDetails", key="#id")
	public boolean deleteRoomById(Integer id) {
		if(roomsRepo.existsById(id))
		{
			roomsRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteHotelById(Integer id) {
		if(hotelRepo.existsById(id))
		{
			hotelRepo.deleteById(id);
			return true;
		}
		return false;
	}

}
