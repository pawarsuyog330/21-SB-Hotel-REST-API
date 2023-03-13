package com.ashokit.rest.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ashokit.rest.entity.HotelEntity;

public interface HotelRepository extends JpaRepository<HotelEntity, Integer> {
	
	@Query("update HotelEntity he set he.name=:name, he.description=:description, he.zipcode=:zipcode, he.isActive=:active where he.id=:id")
	@Modifying
	@Transactional
	int updateHotelDetailsById(@Param(value = "name") String name, @Param(value = "description") String description, @Param(value = "zipcode") Integer zipcode, @Param(value = "active") boolean active, @Param(value = "id") Integer id);
}
