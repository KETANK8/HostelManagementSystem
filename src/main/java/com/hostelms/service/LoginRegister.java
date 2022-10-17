package com.hostelms.service;

import com.hostelms.exception.GlobalException;

public interface LoginRegister {

	public void Register()throws GlobalException;
	public void Login() throws GlobalException;
	
}
