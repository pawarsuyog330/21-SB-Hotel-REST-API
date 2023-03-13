package com.ashokit.rest.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private Integer id;
	
	private String displayName;
	
	private Double price;
	
	private Integer quantity;

}
