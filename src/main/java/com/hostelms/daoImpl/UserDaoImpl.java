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
package com.hostelms.daoImpl;

import com.hostelms.config.HibernateUtil;
import com.hostelms.dao.UserDao;
import com.hostelms.exception.GlobalException;
import com.hostelms.model.User;

import org.hibernate.Session;

public class UserDaoImpl implements UserDao {

	// METHOD 1
	// TO SHOW ROOM DETAILS OF USER
	@Override
	public User userRoom(int uId) {
		// TODO Auto-generated method stub
		try (Session ses = HibernateUtil.getSession()) {

			User u = ses.get(User.class, uId);// FETCHING USER OBJECT
			return u;
		}
	}

	// METHOD 2
	// TO SHOW DUE AMOUNT OF USER
	@Override
	public int userDueAmount(int uId) throws GlobalException {
		// TODO Auto-generated method stub
		try (Session ses = HibernateUtil.getSession()) {
			User u = ses.get(User.class, uId);
			if (u == null) {
				throw new GlobalException("User Does not Exist");
			}
			int amount = (int) ses.createQuery("select userRent from User where userId =: id").setParameter("id", uId)
					.uniqueResult();// FETCHING AMOUNT DETAILS
			return amount;
		}
	}

	// METHOD 3
	// TO SHOW PROFILE DETAILS OF USER
	@Override
	public User userProfile(int uId) {
		// TODO Auto-generated method stub
		try (Session ses = HibernateUtil.getSession()) {

			User u = ses.get(User.class, uId);// FETCHING USER OBJECT
			return u;
		}
	}

	// METHOD 4
	// TO CHANGE CONTACT INFO OF USER
	@Override
	public int changeContact(int uId, String newContact) throws GlobalException {
		// TODO Auto-generated method stub
		try (Session ses = HibernateUtil.getSession()) {
			ses.beginTransaction();
			User u = ses.get(User.class, uId);
			if (u == null) {
				throw new GlobalException("User Does not Exist");
			}
			// UPDATING CONTACT INFO
			int res = ses.createQuery("update User set userContact =: contact where userId =: id")
					.setParameter("contact", newContact).setParameter("id", uId).executeUpdate();
			ses.getTransaction().commit();
			return res;
		}
	}

	// METHOD 5
	// TO CHANGE PASSWORD OF USER PROFILE
	@Override
	public int changePassWord(int uId, String oldPswrd, String newPswrd) throws GlobalException {
		// TODO Auto-generated method stub
		try (Session ses = HibernateUtil.getSession()) {
			ses.beginTransaction();
			User u = ses.get(User.class, uId);

			// VALIDATE IF INPUT PASSWORD IS MATCHES WITH ACCOUNT PASSWORD
			if (u.getUserPassword().equals(oldPswrd)) {

				// COMPARE OLD AND NEW PASSWORD
				// PASSWORD CANNOT CHANGE
				// IF NEW AND OLD PASSWORD IS SAME
				if (oldPswrd.equals(newPswrd)) {
					throw new GlobalException("New Password cannot be same as Old Password");
				}

				// NEW AND OLD PASSWORD IS DIFFERENT
				// UPDATING NEW PASSWORD OF USER PROFILE
				else {
					// UPDATING PASSWORD
					int status = ses.createQuery("update User set userPassword =: newPwd where userId =: id")
							.setParameter("newPwd", newPswrd).setParameter("id", uId).executeUpdate();
					ses.getTransaction().commit();
					return status;
				}
			} else {
				throw new GlobalException("Wrong Password!!!");
			}
		}
	}

	// METHOD 6
	@Override
	public int payRent(int uId, int amount) throws GlobalException {

		try (Session ses = HibernateUtil.getSession()) {
			ses.beginTransaction();
			User u = ses.get(User.class, uId);

			if (amount < 0)
				// THROWING EXCEPTION IF AMOUNT IS INVALID
				throw new GlobalException("Amount Cannot be negative !!!");
			if (u == null)
				// THROWING EXCEPTION IF USER NOT PRESENT IN DATABASE
				throw new GlobalException("User not found !!!");
			int rent = u.getUserRent();
			rent = rent - amount;
			// UPDATING DUE AMOUNT BASED ON AMOUNT IS PAID
			ses.createQuery("update User set userRent =: rent where userId =: id").setParameter("rent", rent)
					.setParameter("id", uId).executeUpdate();
			ses.getTransaction().commit();
			return rent;
		}
	}
}