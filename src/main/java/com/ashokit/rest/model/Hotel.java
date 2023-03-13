package com.ashokit.rest.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Hotel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String description;

	private boolean isActive;

	private String name;

	private Integer zipcode;

	@XmlElementWrapper(name="rooms")
	private List<Room> rooms;

}
