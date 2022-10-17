package com.hostelms;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.hostelms.config.HibernateUtil;
import com.hostelms.dao.AdminDao;
import com.hostelms.daoImpl.AdminDaoImpl;
import com.hostelms.exception.GlobalException;
import com.hostelms.model.Room;
import com.hostelms.model.User;

import org.hibernate.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AdminTest {

	// TEST 1
	@Test
	@DisplayName("CREATE ROOM TESTING")
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
		// NEW ROOM OBJECR
		Room a = new Room();
		a.setRoomId(116);
		a.setRoomName("Room Q");
		a.setRoomType("AC");

		assertAll(

				// POSITIVE TEST CASE
				// TESTING TO ADD NEW ROOM TO DATABASE
				// EXPECTING POSITIVE RESULT TO ADD ROOM IN DATABASE
				() -> assertEquals(1, dao.createRoom(a)),

				// NEGATIVE TEST CASE
				// TESTING TO ADD A ROOM THAT ALREADY EXIST IN DATABASE
				// EXPECTING MRTHOD TO THROW AN EXCEPTION
				() -> assertThrows(GlobalException.class, () -> dao.createRoom(r)));
	}

	// TEST 2
	@Test
	@DisplayName("Allot Room Testing")
	void allotRoomTest() {

		// CREATING ADMIN DAO OBJECT
		AdminDao dao = new AdminDaoImpl();

		assertAll(
				// POSITIVE TEST CASE
				// TESTING TO ALLOT A ROOM TO A USER FROM DATABASE
				// EXPECTING POSITIVE RESULT THAT USER ROOM UPDATED IN DATABASE
				() -> assertEquals(1, dao.allotRoom(15, 101)),

				// NEGATIVE TEST CASE
				// TESTING TO ALLOT A ROOM BY GIVING WRONG ROOM ID
				// GIVING WRONG USER ID IN METHOD
				// EXPECTING METHOD TO THROW AN EXCEPTION
				() -> assertThrows(GlobalException.class, () -> dao.allotRoom(500, 500)));

	}

	// TEST 3
	@Test
	@DisplayName("DELETE USER TESTING")
	void deleteUserTest() {

		// CREATING ADMIN DAO OBJECT
		AdminDao dao = new AdminDaoImpl();

		assertAll(

				// POSTIVE TEST CASE
				// TESTING TO DELETE AN USER FROM DATABASE BY GIVING USER ID

				// EXPECTING TO DELETE USER FROM DATABASE SUCCESSFULLY
				() -> assertEquals(1, dao.deleteUser(17)),

				// NEGATIVE TEST CASE
				// TESTING TO DELETE AN USER WHOSE USER ID DOES NOT EXIST IN DATABASE
				// EXPECTING METHOD TO THROW AN EXCEPTION
				() -> assertThrows(GlobalException.class, () -> dao.deleteUser(500)));
	}

	// TEST 4
	@Test
	@DisplayName("SET RENT TESTING")
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
				() -> assertThrows(GlobalException.class, () -> dao.generateRent(500, 6000)));
	}

	// TEST 5
	@Test
	@DisplayName("RENT PAYMENT TESTING")
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
				() -> assertThrows(GlobalException.class, () -> dao.rentPayment(500, 1000)));
	}

	// TEST 6
	@Test
	@DisplayName("FETCH USER TESTING")
	void viewUserTest() throws GlobalException {

		// CREATING ADMIN DAO OBJECT
		AdminDao dao = new AdminDaoImpl();
		// CREATING SESSSION OBJECT
		Session ses = HibernateUtil.getSession();
		// FETCHING USER DETAIL USING SESSION OBJECT
		User u = ses.get(User.class, 1);
		// FETCHING SAME USER USING FETCH USER PROFILR METHOD
		User u1 = dao.fetchUserProfile(1);
		assertAll(

				// POSTIVE TEST CASE
				// TESTING TO COMPARE BOTH USER THAT WE FETCHED EARLIER
				// EXPECTING THAT BOTH ARE EQUAL/SAME
				() -> assertEquals(u.toString(), u1.toString()),

				// NEGATIVE TEST CASE
				// TESTING FETCH USER PROFILE METHOD FOR INCORRECT USER ID
				// EXCEPTING METHOD TO THROW AN EXCEPTION
				() -> assertThrows(GlobalException.class, () -> dao.fetchUserProfile(500)));
	}
}