package com.hostelms.config;

import java.util.Properties;

import com.hostelms.model.Room;
import com.hostelms.model.User;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class HibernateUtil {

	// creating session factory object
	private static SessionFactory sesfac;

	// getting logger in hibernateUtil class
	static Logger log = Logger.getLogger(HibernateUtil.class);

	public static SessionFactory getSessionFactory() {

		// create a new session factory if not exist
		if (sesfac == null) {

			// setting properties of hibernate to connect with database
			try {
				Configuration config = new Configuration();
				Properties pro = new Properties();

				// setting hibernate properties to access database
				pro.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver"); // getting mysql driver
				pro.put(Environment.URL, "jdbc:mysql://localhost:3306/hostel"); // connecting with database
				pro.put(Environment.USER, "root"); // user name of mysql server
				pro.put(Environment.PASS, "142307"); // password of mysql server
				pro.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect"); // setting mysql dialect
				pro.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread"); // checking the session by thread of
																				// execution
				pro.put(Environment.HBM2DDL_AUTO, "update"); // creating or updating the table if already exist
				pro.put(Environment.SHOW_SQL, "false"); // by indicating false we are not going to show sql process in
														// our console

				config.setProperties(pro); // configure properties that we created
				config.addAnnotatedClass(User.class); // adding entity classes
				config.addAnnotatedClass(Room.class);
				sesfac = config.buildSessionFactory();

			} catch (Exception e) { // if any exception occur handaling the exception
				log.info(e.getMessage());
			}
		}
		return sesfac;
	}

	// getSession method to create a new session from sessionFactory pool
	// everytime this method is called
	public static Session getSession() {

		return getSessionFactory().openSession();
	}
}
