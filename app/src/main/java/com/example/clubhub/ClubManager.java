package com.example.clubhub;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ClubManager{
	
	// PLaceholder(?) for the club the user is currently viewing
	public static String currentClubID;
	
	// Local version of the clubs stored in the database to prevent asynch issues
	private static HashMap<String, Club> clubs = new HashMap<>();


	/**
	 * Creates a new Club object and stores it in the database with
	 * a random, unique key
	 * Assigns the club a club school ID
	 * @param name the name of the new club
	 * @param schoolID the ID of the school this club belongs to
	 */
	public static void createClub(String name, String schoolID) {

		DatabaseReference clubsRef = FirebaseDatabase.getInstance().getReference("clubs").push();
		String key = clubsRef.getKey();
		// Placeholder for user info
		currentClubID = key;
		// Put student creator here
		clubsRef.setValue(new Club(name, key, schoolID, UserManager.currentUserID));
		SchoolManager.getSchool(schoolID).addClub(key);


		UserManager.getUserData(UserManager.currentUserID).addUserToClubAsLeader(key);

	}
	
	/**
	 * Returns a Club object with the given ID
	 * @param ID The ID used to find the Club
	 * @return The Club with the given IO
	 */
	public static Club getClub(String ID) {
		return clubs.get(ID);
	}

	/**
	 * Puts a club into the ClubManager's club Hashmap
	 * ONly to be used by the initiateClubs method
	 * @param ID
	 * @param s
	 */
	public static void putClub(String ID, Club s) {
		clubs.put(ID, s);
	}
}
