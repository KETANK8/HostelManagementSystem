/**
 * HOSTEL   MANAGEMENT    SYSTEM
 * @author Ketan Kumar
 * Illustrating USE OF LOMBOK,LOGGER AND GLOBAL EXCEPTION IN HOSTEL MANAGEMENT SYSTEM 
 * TO CREATE USER,ROOM ADD ROOM AND USER TO DATABASE USING LOMBOK INHRITANCE IN HIBERNATE 
 * ALLOTING ROOM TO USER
 * THERE ARE TWO TYPES OF USER
 * ->ADMIN
 * ->END USER
 * AND PRINT DATA OF ONE OR ALL USER USING LOGGER, DELETE USER AND ROOM USING DATA ACCESS OBJECT AND HQL 
 * CREATING AND USING GLOBAL EXCEPTION
 * ILLUSTRATING OBJECT RELATION MAPPING IN ENTITY USING HIBERNATE
 * ONE ROOM CAN HAVE MANY USER
 */
package com.hostelms;

import java.util.Scanner;

import com.hostelms.exception.GlobalException;
import com.hostelms.service.LoginRegister;
import com.hostelms.serviceImpl.LoginRegisterImpl;

import org.apache.log4j.Logger;

public class Hostel {
	// Logger records the flow of the execution of the project as per
	// log4j.properties
	static Logger log = Logger.getLogger(Hostel.class);

	public static void main(String[] args) {
		// Creating Scanner Object in try with resources way...
		try (Scanner scan = new Scanner(System.in)) {

			log.info("Hostel Management System");
			LoginRegister loginReg = new LoginRegisterImpl();
			int choice = 0;
			while (choice < 4) {
				log.info(
						"\nPress 1 - Register New Profile \nPress 2 - Profile Login \nPress 3 - Exit \nEnter Your Choice : ");
				choice = scan.nextInt();
				switch (choice) {
					// First Case
					// TO Register New Profile
					case 1 -> {
						try {
							loginReg.Register();
						} catch (GlobalException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					// Second Case
					// TO Login to Existing Profile
					case 2 -> {
						try {
							loginReg.Login();
						} catch (GlobalException e) {// TODO Auto-generated catch block

							log.info(e.getMessage());
						}
					}

					// DEFAULT CASE TO EXIT
					// TERMINATE THE EXECUTION OF PROGRAM
					default -> System.exit(0);
				}
			}
		}

	}
}
