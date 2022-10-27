package com.hostelms;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.hostelms.config.HibernateUtil;
import com.hostelms.dao.AdminDao;
import com.hostelms.daoImpl.AdminDaoImpl;
import com.hostelms.exception.GlobalException;
import com.hostelms.model.Room;
import com.hostelms.model.User;

import org.hibernate.Session;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AdminTest {

	// TEST 1.1
	@Test
	// @Disabled
	@DisplayName("POSITIVE-CREATE ROOM TESTING")
	void addRoomTest() {

		// CREATING ADMIN DAO OBJECT
		AdminDao dao = new AdminDaoImpl();

		// CREATING ROOM OBJECT
		// ROOM ALREADY EXIST IN DATABASE
		Room r = new Room();
		r.setRoomId(100);
		r.setRoomName("Room");
		r.setRoomType("Ac");

		// CREATING ROOM OBJECT
		// NEW ROOM OBJECT
		Room a = new Room();
		a.setRoomId(121);
		a.setRoomName("Room L");
		a.setRoomType("AC");

		assertAll(
				// POSITIVE TEST CASE
				// TESTING TO ADD NEW ROOM TO DATABASE
				// EXPECTING POSITIVE RESULT TO ADD ROOM IN DATABASE
				() -> assertEquals(1, dao.createRoom(a)),
				// TESTING TO ADD A ROOM THAT ALREADY EXIST IN DATABASE
				// EXPECTING MRTHOD TO THROW AN EXCEPTION
				() -> assertEquals(0, dao.createRoom(r)));
	}

	// Test 1.2
	@Test
	@Disabled
	@DisplayName("NEGATIVE-CREATE ROOM TESTING")
	void addRoomTest2() {
		// CREATING ADMIN DAO OBJECT
		AdminDao dao = new AdminDaoImpl();
		// CREATING ROOM OBJECT
		// NEW ROOM OBJECT
		Room a = new Room();
		a.setRoomId(100);
		a.setRoomName("Room");
		a.setRoomType("AC");
		// NEGATIVE TEST CASE
		// TESTING TO ADD A ROOM THAT ALREADY EXIST IN DATABASE
		// EXPECTING MRTHOD TO THROW AN EXCEPTION
		assertEquals(1, dao.createRoom(a));
	}

	// TEST 2.1
	@Test
	// @Disabled
	@DisplayName("Positive-Allot Room Testing")
	void allotRoomTest() {

		// CREATING ADMIN DAO OBJECT
		AdminDao dao = new AdminDaoImpl();

		assertAll(
				// POSITIVE TEST CASE
				// TESTING TO ALLOT A ROOM TO A USER FROM DATABASE
				// EXPECTING POSITIVE RESULT THAT USER ROOM UPDATED IN DATABASE
				() -> assertEquals(1, dao.allotRoom(15, 102)),

				// NEGATIVE TEST CASE
				// TESTING TO ALLOT A ROOM BY GIVING WRONG ROOM ID
				// GIVING WRONG USER ID IN METHOD
				// EXPECTING METHOD TO THROW AN EXCEPTION
				() -> assertEquals(0, dao.allotRoom(500, 500)));

	}

	// TEST 2.2
	@Test
	@Disabled
	@DisplayName("Negative-Allot Room Testing")
	void allotRoomTest2() {
		// CREATING ADMIN DAO OBJECT
		AdminDao dao = new AdminDaoImpl();
		// NEGATIVE TEST CASE
		// TESTING TO ALLOT A ROOM BY GIVING WRONG ROOM ID
		// GIVING WRONG USER ID IN METHOD
		// EXPECTING METHOD TO RETURN 1 BUT ACTUALLY IT IS EXCEPTION
		assertEquals(1, dao.allotRoom(500, 500));

	}

	// TEST 3.1
	@Test
	// @Disabled
	@DisplayName("POSITIVE-DELETE USER TESTING")
	void deleteUserTest() {

		// CREATING ADMIN DAO OBJECT
		AdminDao dao = new AdminDaoImpl();

		assertAll(

				// POSTIVE TEST CASE
				// TESTING TO DELETE AN USER FROM DATABASE BY GIVING USER ID

				// EXPECTING TO DELETE USER FROM DATABASE SUCCESSFULLY
				() -> assertEquals(1, dao.deleteUser(18)),

				// NEGATIVE TEST CASE
				// TESTING TO DELETE AN USER WHOSE USER ID DOES NOT EXIST IN DATABASE
				// EXPECTING METHOD TO THROW AN EXCEPTION
				() -> assertEquals(0, dao.deleteUser(500)));
	}

	// TEST 3.2
	@Test
	@Disabled
	@DisplayName("NEGATIVE-DELETE USER TESTING")
	void deleteUserTest2() {

		// CREATING ADMIN DAO OBJECT
		AdminDao dao = new AdminDaoImpl();

		// NEGATIVE TEST CASE
		// TESTING TO DELETE AN USER WHOSE USER ID DOES NOT EXIST IN DATABASE
		// EXPECTING METHOD TO RETURN 1 BUT ACTUALLY IT IS EXCEPTION
		assertEquals(1, dao.deleteUser(500));
	}

	// TEST 4.1
	@Test
	// @Disabled
	@DisplayName("POSITIVE-SET RENT TESTING")
	void setRentTest() {

		// CREATING ADMIN DAO OBJECT
		AdminDao dao = new AdminDaoImpl();

		assertAll(
				// POSITIVE TEST CASE
				// TESTING TO UPDATE RENT AMOUNT WITH 5000 RS FOR USER ID 4
				// EXPECTING GENERATED RENT OF 5000 FOR USER 4
				() -> assertEquals(1, dao.generateRent(4, 5000)),

				// NEGATIVE TEST CASE
				// TESTING TO UPDATE RENT WITH 6000 FOR INCORRECT USER ID
				// EXPECTING METHOD TO THROW AN EXCEPTION
				() -> assertEquals(0, dao.generateRent(500, 6000)));
	}

	// TEST 4.2
	@Test
	@Disabled
	@DisplayName("NEGATIVE-SET RENT TESTING")
	void setRentTest2() {

		// CREATING ADMIN DAO OBJECT
		AdminDao dao = new AdminDaoImpl();

		// NEGATIVE TEST CASE
		// TESTING TO UPDATE RENT WITH 6000 FOR INCORRECT USER ID
		// EXPECTING METHOD TO THROW AN EXCEPTION
		assertEquals(1, dao.generateRent(500, 6000));
	}

	// TEST 5.1
	@Test
	// @Disabled
	@DisplayName("POSITIVE-RENT PAYMENT TESTING")
	void payRentTest() {

		AdminDao dao = new AdminDaoImpl();

		assertAll(
				// POSITIVE TEST CASE
				// TESTING TO PAY 1000 AMOUNT AS RENT PAYMENT
				// EXEPCTING 9000 RENT AMOUNT LEFT TO PAY
				() -> assertEquals(9000, dao.rentPayment(5, 1000)),

				// NEGATIVE TEST CASE
				// TESTING FOR INCORRECT USER ID
				// EXCEPTING METHOD TO THROW AN EXCEPTION
				() -> assertEquals(0, dao.rentPayment(500, 1000)));
	}

	// TEST 5.2
	@Test
	@Disabled
	@DisplayName("NEGATIVE-RENT PAYMENT TESTING")
	void payRentTest2() {

		AdminDao dao = new AdminDaoImpl();
		// NEGATIVE TEST CASE
		// TESTING FOR INCORRECT USER ID
		// EXCEPTING METHOD TO THROW AN EXCEPTION
		assertEquals(1, dao.rentPayment(500, 1000));
	}

	// TEST 6.1
	@Test
	// @Disabled
	@DisplayName("POSITIVE-FETCH USER TESTING")
	void viewUserTest() throws GlobalException {

		// CREATING ADMIN DAO OBJECT
		AdminDao dao = new AdminDaoImpl();
		// CREATING SESSSION OBJECT
		Session ses = HibernateUtil.getSession();
		// FETCHING USER DETAIL USING SESSION OBJECT
		User u = ses.get(User.class, 1);
		// FETCHING SAME USER USING FETCH USER PROFILE
		User u1 = dao.fetchUserProfile(1);

		// POSTIVE TEST CASE
		// TESTING TO COMPARE BOTH USER THAT WE FETCHED EARLIER
		// EXPECTING THAT BOTH ARE EQUAL/SAME
		assertEquals(u.toString(), u1.toString());

	}

	// TEST 6.2
	@Test
	@Disabled
	@DisplayName("NEGATIVE-FETCH USER TESTING")
	void viewUserTest2() throws GlobalException {

		// CREATING ADMIN DAO OBJECT
		AdminDao dao = new AdminDaoImpl();
		// CREATING SESSSION OBJECT
		Session ses = HibernateUtil.getSession();
		// FETCHING USER DETAIL USING SESSION OBJECT
		User u = ses.get(User.class, 1);
		// FETCHING Different USER USING FETCH USER PROFILE METHOD
		User u2 = dao.fetchUserProfile(2);

		// NEGATIVE TEST CASE
		// TESTING FETCH USER PROFILE METHOD FOR INCORRECT USER ID
		// EXCEPTING METHOD TO THROW AN EXCEPTION
		assertEquals(u.toString(), u2.toString());

	}
}