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
package com.HostelMS.serviceImpl;

import java.util.Scanner;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.HostelMS.App;
import com.HostelMS.ModelDTO.UserDTO;
import com.HostelMS.dao.HostelMSDao;
import com.HostelMS.daoImpl.HostelMSDaoImpl;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.User;
import com.HostelMS.service.LoginRegister;

import org.apache.log4j.Logger;

public class LoginRegisterImpl implements LoginRegister{

	static Logger log = Logger.getLogger(App.class);
	static Scanner scan = new Scanner(System.in);
	static HostelMSDao dao = new HostelMSDaoImpl();
	
	@Override
	public void Register() throws GlobalException {
		// TODO Auto-generated method stub
		// CREATING DTO OBJECT
		UserDTO ud = new UserDTO();
		// CREATING USER ENTITY OBJECT
		User u = new User();
		log.info("REGISTRATION");
		log.info("Enter Your First Name : ");
		String fName = scan.next();
		ud.setFirstName(fName);
		log.info("Enter Your Last Name : ");
		String lName = scan.next();
		ud.setLastName(lName);
		log.info("Create Unique Username");
		String uname=scan.next();
		ud.setUserName(uname);
		log.info("Create Password");
		String upwd=scan.next();
		ud.setUserPassword(upwd);
		log.info("Enter Contact number");
		String uphone=scan.next();
		ud.setUserContact(uphone);
		log.info("Enter Your Address");
		String uaddress=scan.next();
		ud.setUserAddress(uaddress);
		ud.setUserRole("student");
		ud.setUserRoom(null);
		ud.setUserRent(0);
		
		// CREATING VALIDATOR FACTORY OBJECT
		ValidatorFactory validfac = Validation.buildDefaultValidatorFactory();
		// CREATING VALIDATOR TO CHECK VALIDATION
		Validator valid = validfac.getValidator();
		
		// Checking Validation to Set Unique UserName
		// Checking Validation to Set Unique Password
		// Checking Validation to Set Contact Number
		Set<ConstraintViolation<UserDTO>> violations =	valid.validate(ud);
		
		// IF ANY VALIDATION IS FAILED 
		// USER WILL NOT SAVED
		// AN ERROR MESSAGE DISPLAY IN RESPECT OF VALIDATION THAT FAILED
		if(violations.size()>0)
		{
			for(ConstraintViolation<UserDTO> violates : violations)
				log.info (violates.getMessage());// SHOWING VALIDATION MESSAGE
		}
		else {
			// saving the user details
			// IF DTO PASS THE VALIDATION CHECK
			u.setFirstName(fName);
			u.setLastName(lName);
			u.setUserName(uname);
			u.setUserAddress(uaddress);
			u.setUserContact(uphone);
			u.setUserPassword(upwd);
			u.setUserRent(ud.getUserRent());
			u.setUserRole(ud.getUserRole());
			u.setUserRoom(ud.getUserRoom());
			
			int status=dao.Registration(u);
			if(status==1) {
				log.info(uname+" Register successfully.");
			}
			else {
				throw new GlobalException("Something went wrong");
			}
		}
		
		
	}

	@Override
	public void Login() {
		// TODO Auto-generated method stub
		log.info("Profile Login");
		log.info("Enter Your Username");
		String username=scan.next();
		log.info("Enter Your Password");
		String password=scan.next();
		User u = null;
		try {
			u = dao.Login(username, password);
			log.info("\nLogin Successfull"+"\nWelcome  "+u.getFirstName()+" "+u.getLastName());
			
			// CREATING OBJECT OF ADMIN AND USER DASHBOARD
			adminDashboardImpl adimpl = new adminDashboardImpl();
			userDashboardImpl udimpl=new userDashboardImpl();
			
			// CALLING ADMIN OR USER DASHBOARD 
			// BASED ON USER ROLE
			// IF USER ROLE IS ADMIN THEN ONLY ADMIN DASHBOARD IS CALLED
			if(u.getUserRole().equals("admin")) {
				adimpl.dashboard();
			}
			else {
				udimpl.dashboard(u.getUserId());
			}
		} catch (GlobalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}	
}
