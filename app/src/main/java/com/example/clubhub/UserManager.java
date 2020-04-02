package com.example.clubhub;


import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserManager{


	private static HashMap<String, UserData> users  = new HashMap<String, UserData>();

	public static String currentUserID;


	/**
	 * Creates a new User object and stores it in the database with
	 * a random, unique key
	 * Assigns the user a school ID
	 * @param name the name of the new user
	 * @param schoolID the ID of the school this user belongs to
	 */
	public static void createUser(String name, String schoolID) {

		DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users").push();
		String key = usersRef.getKey();
		// Placeholder for user info
		currentUserID = key;
		Log.v("MainActivity", "Current User: "+ currentUserID);
		usersRef.setValue(new UserData(name, key, schoolID));


	}
	
	/**
	 * Returns a UserData object with the given ID
	 * @param ID The ID used to find the UserData
	 * @return The UserData with the given ID
	 */
	public static UserData getUserData(String ID) {

		return users.get(ID);
	}

	/**
	 * Puts a user into the UserManager's club Hashmap
	 * ONly to be used by the initiateUsers method
	 * @param ID
	 * @param s
	 */
	public static void putUser(String ID, UserData s){
		users.put(ID, s);
	}
	

}
