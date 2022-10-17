package com.hostelms.dao;

import com.hostelms.exception.GlobalException;
import com.hostelms.model.User;

public interface UserDao {

	public User userRoom(int uId);

	public int userDueAmount(int uId) throws GlobalException;

	public User userProfile(int uId);

	public int changeContact(int uId, String newContact) throws GlobalException;

	public int changePassWord(int uId, String oldPswrd, String newPswrd) throws GlobalException;

	public int payRent(int uId, int amount) throws GlobalException;
}
