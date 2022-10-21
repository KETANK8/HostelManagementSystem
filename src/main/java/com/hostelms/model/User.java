package com.hostelms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@NotNull
	private String userName;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private String userContact;
	@NotNull
	private String userPassword;
	private String userAddress;
	private String userRole;
	private int userRent;

	@ManyToOne
	private Room userRoom;
}
