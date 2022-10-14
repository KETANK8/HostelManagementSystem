package com.HostelMS.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	
	// Using Regular Expression
	// RegEx To Check Validation
	// Giving Condition to Set Unique UserName
	@NotNull
	private String userName;
	
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	
	// Using Regular Expression
	// RegEx To Check Validation	
	// Giving Condition to Set Contact Number
	@NotNull
	
	private String userContact;

	// Using Regular Expression
	// RegEx To Check Validation	
	// Giving Condition to Set Unique Password
	@NotNull
	private String userPassword;
	private String userAddress;
	private String userRole;
	private int userRent;
	
	@ManyToOne
	private Room userRoom;
}
