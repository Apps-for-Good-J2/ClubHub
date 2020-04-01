package com.example.clubhub;


import java.util.HashMap;

public class ClubManager{
	
	// Placeholder variable for the IDs given by firebase
	public static int countID = 0;

	public static int currentClubID;
	
	// This will be stored on firebase cloud storage in real implementation
	// The will be a 'SchoolController' and serve as the bridge between the logic and database.
	private static HashMap<Integer, Club> clubs = new HashMap<Integer, Club>();

	
	/**
	 * Creates a new club object with a given name, and under a certain school
	 * @param name
	 * @param schoolID
	 */

	// make static when pulling from firebase?
	public static void createClub(String name, int schoolID) {
		Club club = new Club(name, countID, schoolID);
		clubs.put(countID, club);


		SchoolManager.getSchool(schoolID).addClub(countID);

		// Currently does not update the list of clubs
		// under the student creator or the list of students in
		// this club

		countID++;
	}
	
	/**
	 * Returns a Club object with the given ID
	 * @param ID The ID used to find the Club
	 * @return The Club with the given IO
	 */
	public static Club getClub(int ID) {
		return clubs.get(ID);
	}
}
