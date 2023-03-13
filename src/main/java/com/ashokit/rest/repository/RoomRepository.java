package com.ashokit.rest.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ashokit.rest.entity.RoomEntity;

public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
	
	@Query(value="select * from rooms where hotel_id=? and id=?", nativeQuery = true)
	Optional<RoomEntity> findRoomByHotelAndRoomId(Integer hotelId, Integer roomId);
	
	@Query(value="update RoomEntity re set re.displayName=:displayName, re.quantity=:quantity, re.price=:price where re.id=:id")
	@Modifying
	@Transactional
	int updateHotelRoomDetailsById(@Param(value = "displayName") String displayName, @Param(value = "quantity") Integer quantity, @Param(value = "price") Double price, @Param(value = "id") Integer id);
	
	

}
