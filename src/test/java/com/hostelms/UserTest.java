package com.hostelms;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.hostelms.dao.UserDao;
import com.hostelms.daoImpl.UserDaoImpl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

	// TEST 1.1
	@Test
	// @Disabled
	@DisplayName("POSITIVE-DUE RENT TESTING")
	void dueAmountTest() {

		// CREATING USER DAO METHOD
		UserDao dao = new UserDaoImpl();

		assertAll(
				// POSITIVE TEST CASE
				// TESTING TO FETCH RENT BILL FOR USER 2
				// EXPECTING TO FETCH 10000 RENT AMOUNT FROM USER 2
				() -> assertEquals(10000, dao.userDueAmount(2)),

				// NEGATIVE TEST CASE
				// TESTING USER DUE AMOUNT METHOD FOR WRONG USER ID

				() -> assertEquals(0, dao.userDueAmount(500)));
	}

	// TEST 1.2
	@Test
	@Disabled
	@DisplayName("NEGATIVE-DUE RENT TESTING")
	void dueAmountTest2() {

		// CREATING USER DAO METHOD
		UserDao dao = new UserDaoImpl();

		// NEGATIVE TEST CASE
		// TESTING USER DUE AMOUNT METHOD FOR WRONG USER ID
		assertEquals(10000, dao.userDueAmount(500));
	}

	// TEST 2.1
	@Test
	// @Disabled
	@DisplayName("POSITIVE-CHANGE CONTACT TESTING")
	void changeContactTest() {

		// CREATING USER DAO METHOD
		UserDao dao = new UserDaoImpl();

		assertAll(
				// POSITIVE TEST CASE
				// TESTING CHANGE CONTACT METHOD TO CHAGE CONTCT OF USER 2
				// EXPECTING TO CHANGE USER 2 CONTACT DETAIL IN DATABSE
				() -> assertEquals(1, dao.changeContact(2, "9899999999")),

				// NEGATIVE TEST CASE
				// TESTING CHANGE CONTACT METHOD FOR WRONG USER ID

				() -> assertEquals(0, dao.changeContact(500, "9999999999")));
	}

	// TEST 2.2
	@Test
	@Disabled
	@DisplayName("NEGATIVE-CHANGE CONTACT TESTING")
	void changeContactTest2() {

		// CREATING USER DAO METHOD
		UserDao dao = new UserDaoImpl();
		// NEGATIVE TEST CASE
		// TESTING CHANGE CONTACT METHOD FOR WRONG USER ID
		assertEquals(1, dao.changeContact(500, "9999999999"));
	}

	// TEST 3.1
	@Test
	// @Disabled
	@DisplayName("POSITIVE-CHANGE PASSWORD TESTING")
	void cahngePasswordTest() {

		UserDao dao = new UserDaoImpl();

		assertAll(
				// POSITIVE TEST CASE
				// TESTING TO CHANGE PASSWORD OF USER 2
				// EXPECTING TO CHANGE PASSWORD SUCCESSFULLY
				() -> assertEquals(1, dao.changePassWord(2, "AK12345@", "amit1234")),

				// NEGATIVE TEST CASE
				// TESTING TO CHANGE PASSWORD OF A USER THAT NOT EXIST IN DATABASE

				() -> assertEquals(0, dao.changePassWord(4, "amit1234", "AK12345@")));

	}

	// TEST 3.2
	@Test
	@Disabled
	@DisplayName("NEGATIVE-CHANGE PASSWORD TESTING")
	void cahngePasswordTest2() {

		UserDao dao = new UserDaoImpl();

		// NEGATIVE TEST CASE
		// TESTING TO CHANGE PASSWORD OF A USER THAT NOT EXIST IN DATABASE

		assertEquals(1, dao.changePassWord(4, "amit1234", "AK12345@"));
	}
}