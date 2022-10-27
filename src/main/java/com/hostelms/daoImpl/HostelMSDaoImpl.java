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
import com.hostelms.dao.HostelMSDao;
import com.hostelms.exception.GlobalException;
import com.hostelms.model.User;

import org.apache.log4j.Logger;
import org.hibernate.Session;

public class HostelMSDaoImpl implements HostelMSDao {

	// getting logger in HostelMSDaoImpl class
	static Logger log = Logger.getLogger(HostelMSDaoImpl.class);

	@Override
	public int Registration(User u) {
		// TODO Auto-generated method stub
		try (Session ses = HibernateUtil.getSession()) {

			String name = u.getUserName();
			User u2 = null;
			u2 = (User) ses.createQuery("from User where userName =: Name").setParameter("Name", name).uniqueResult();
			if (u2 == null) {
				ses.beginTransaction();
				ses.save(u);
				ses.getTransaction().commit();
			} else {
				throw new GlobalException("User Name alreay taken!!!");
			}

		} catch (GlobalException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
		return 1;
	}

	@Override
	public User Login(String UserName, String password) {
		// TODO Auto-generated method stub
		User u = null;
		try (Session ses = HibernateUtil.getSession()) {
			ses.beginTransaction();

			User u2 = (User) ses.createQuery("from User where userName=:username").setParameter("username", UserName)
					.uniqueResult();
			if (u2 != null) {
				if (u2.getUserPassword().equals(password)) {
					return u2;
				} else {
					throw new GlobalException("Wrong Username or Password!!!");
				}
			} else {
				throw new GlobalException("User does not exist!!!");
			}

		} catch (GlobalException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
		return u;
	}

}
