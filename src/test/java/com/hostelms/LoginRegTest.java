/**
 * HOSTEL   MANAGEMENT    SYSTEM
 * @author Ketan Kumar
 * Illustrating UNIT TESTING OF HOSTEL MANAGEMENT SYSTEM
 * THERE ARE TWO TYPES OF USER
 * ->ADMIN
 * ->END USER
 * UNIT TESTING OF PRINT DATA OF ONE OR ALL USER USING LOGGER, DELETE USER AND ROOM USING DATA ACCESS OBJECT AND HQL 
 * TESTING OF METHOD OF HOTELMS, USER AND ADMIN DASHBOARD METHOD USING JUNIT TEST CASES
 * USED POSITIVE AND NEGATIVE TEST CASES TO PERFORM TESTING OF FOLLOWING METHOD 
 */
package com.hostelms;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.hostelms.config.HibernateUtil;
import com.hostelms.dao.HostelMSDao;
import com.hostelms.daoImpl.HostelMSDaoImpl;
import com.hostelms.exception.GlobalException;
import com.hostelms.model.User;
import com.hostelms.modeldto.UserDTO;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginRegTest {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {

		// CREATING VALIDATOR FACTORY OBJECT
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		// CREATING VALIDATOR TO CHECK VALIDATION
		validator = factory.getValidator();

	}

	// TEST 1
	@Test
	@DisplayName("REGISTRATION TESTING")
	void registrationTest() {

		// CREATING USER OBJECT THAT ALREADY EXIST IN DATABASE
		UserDTO a = new UserDTO();
		a.setFirstName("Rohit");
		a.setLastName("Sharma");
		a.setUserName("RohitSharma");
		a.setUserPassword("R12345@");
		a.setUserContact("9876543219");
		a.setUserAddress("Delhi");

		// CREATING NEW USER OBJECT
		UserDTO b = new UserDTO();
		b.setFirstName("Rohit");
		b.setLastName("Sharma");
		b.setUserName("RS");
		b.setUserPassword("R123");
		b.setUserContact("9876543219");
		b.setUserAddress("Delhi");

		// Checking Validation to Set Unique UserName
		// Checking Validation to Set Unique Password
		// Checking Validation to Set Contact Number
		Set<ConstraintViolation<UserDTO>> constraintViolations = validator.validate(a);
		// POSITIVE TEST CASE
		assertEquals(0, constraintViolations.size());

		Set<ConstraintViolation<UserDTO>> constraintViolations2 = validator.validate(b);
		// NEGATIVE TEST CASE
		assertEquals(1, constraintViolations2.size());

	}

	// TEST 2
	@Test
	@DisplayName("LOGIN TESTING")
	void loginTest() throws GlobalException {

		// CREATING DAO OBJECT OF HOSTEL MS
		HostelMSDao dao = new HostelMSDaoImpl();

		// CREAYTING SESSION OBJECT
		Session ses = HibernateUtil.getSession();

		// CREATING TWO USER OBJECT
		// FETCHING USER FROM DATA USING SESSION OBJECT
		User u = ses.get(User.class, 1);

		// FETCHING SAME USER FROM DATABASE USING DAO OBJECT AND PRIMARY KEY
		User a = dao.Login("KetanK", "K12345@");

		assertAll(
				// POSITIVE TEST CASE
				// TESTING TO COMPARE IF BOTH USER OBJECT IS SAME
				// EXPECTING SAME RESULT
				() -> assertEquals(u.toString(), a.toString()),

				// NEGATIVE TEST CASE
				// TESTING TO LOGIN USING WRONG USERNAME AND PASSWORD IN LOGIN METHOD
				// EXPECTING METHOD TO THROW AN EXCEPTION
				() -> assertThrows(GlobalException.class, () -> dao.Login("KetanKumar", "K123456")));
	}
}
