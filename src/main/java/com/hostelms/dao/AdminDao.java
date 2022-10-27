package com.hostelms.dao;

import java.util.List;

import com.hostelms.model.Room;
import com.hostelms.model.User;

public interface AdminDao {

	List<Room> AllRooms();

	List<User> AllUsers();

	int createRoom(Room r);

	int allotRoom(int uId, int rId);

	int deleteUser(int uId);

	List<User> userInARoom(int rId);

	int generateRent(int uId, int amount);

	int rentPayment(int uId, int amount);

	int setRole(int uId, String role);

	User fetchUserProfile(int uId);

	List<Room> VaccantRooms();

	List<User> UnAllotedUsers();

	List<User> vaccantBedInRoom(int rId);
}
