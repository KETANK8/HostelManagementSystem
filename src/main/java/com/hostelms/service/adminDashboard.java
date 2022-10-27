package com.hostelms.service;

public interface AdminDashboard {

	void dashboard();

	void fetchAllRooms();

	void fetchAllUsers();

	void createRoom();

	void allotRoom();

	void deleteUser();

	void userInARoom();

	void generateRent();

	void rentPayment();

	void setUserRole();

	void viewUserProfile();

	void vaccantRoom();

	void unAllotedUser();
}
