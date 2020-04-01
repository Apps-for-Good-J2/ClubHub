package com.example.clubhub;


import java.util.HashMap;

public class UserManager{
	
	// Placeholder variable for the IDs given by firebase
	public static int countID = 0;

	// Temp var for testing ONLY,
	// placeholder for the firebase current user object reference
	public static int currentUserID;
	
	// This will be stored on firebase cloud storage in real implementation
	// The will be a 'SchoolController' and serve as the bridge between the logic and database.
	private static HashMap<Integer, UserData> users  = new HashMap<Integer, UserData>();

	
	public static void createUser(String name) {
		UserData data = new UserData(name, countID);
		users.put(countID, data);
		
		countID++;
	}
	
	/**
	 * Returns a UserData object with the given ID
	 * @param ID The ID used to find the UserData
	 * @return The UserData with the given IO
	 */
	public static UserData getUserData(int ID) {
		return users.get(ID);
	}
	

}
