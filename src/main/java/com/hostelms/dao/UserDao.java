package com.hostelms.dao;

import com.hostelms.model.User;

public interface UserDao {

	public User userRoom(int uId);

	public int userDueAmount(int uId);

	public User userProfile(int uId);

	public int changeContact(int uId, String newContact);

	public int changePassWord(int uId, String oldPswrd, String newPswrd);

	public int payRent(int uId, int amount);
}
