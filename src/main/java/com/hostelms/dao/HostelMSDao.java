package com.hostelms.dao;

import com.hostelms.exception.GlobalException;
import com.hostelms.model.User;

public interface HostelMSDao {

	public int Registration(User u) throws GlobalException;
	public User Login(String UserName,String password) throws GlobalException;
}
