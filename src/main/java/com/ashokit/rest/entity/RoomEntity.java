package com.ashokit.rest.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ROOMS")
public class RoomEntity {
	
	@Id
	private Integer id;
	
	private String displayName;
	
	private Double price;
	
	private Integer quantity;

}
