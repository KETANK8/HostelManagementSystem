package com.hostelms.dao;

import com.hostelms.model.User;

public interface HostelMSDao {

	public int Registration(User u);

	public User Login(String UserName, String password);
}
