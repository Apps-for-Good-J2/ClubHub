package com.example.clubhub;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ClubManager{
	
	// Placeholder(?) for the club the user is currently viewing
	public static String currentClubID;
	
	// Local version of the clubs stored in the database to prevent asynch issues
	// and getting information
	private static HashMap<String, Club> clubs = new HashMap<>();

	private static FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();


	/**
	 * Creates a new Club object and stores it in the database with
	 * a random, unique key
	 * Assigns the club a club school ID
	 * @param name the name of the new club
	 * @param description the description of the new club
	 */
	public static void createClub(String name, String description) {

		DatabaseReference clubsRef = FirebaseDatabase.getInstance().getReference("clubs").push();
		String key = clubsRef.getKey();
		// Placeholder
		currentClubID = key;

		String schoolID = UserManager.getUserData(mUser.getUid()).getSchoolID();
		String userID = mUser.getUid();

		clubsRef.setValue(new Club(name, key, schoolID, userID, description));

		// Adds this club to the school's clubs
		SchoolManager.getSchool(schoolID).addClubToCurrentSchool(key);

		/** WILL NOT WORK MUST RECREATE WITH DIFF. DATABASE CALL **/
		UserManager.getUserData(userID).addUserToClubAsLeader(key);

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


	// Do these as a method here or in the club class?
	public static void changeClubName(String clubID, String newName){
		DatabaseReference clubsRef = FirebaseDatabase.getInstance().getReference("clubs");
		clubsRef.child(clubID).child("name").setValue(newName);
	}

	public static void changeClubDescription(String clubID, String newDescription){
		DatabaseReference clubsRef = FirebaseDatabase.getInstance().getReference("clubs");
		clubsRef.child(clubID).child("description").setValue(newDescription);
	}
}
