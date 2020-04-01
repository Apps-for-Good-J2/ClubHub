package com.example.clubhub;


import java.util.HashMap;

public class SchoolManager {
	
	// Placeholder variable for the IDs given by firebase
	public static int countID = 0;

	public static int currentSchoolID;
	
	// This will be stored on firebase cloud storage in real implementation
	// The will be a 'SchoolController' and serve as the bridge between the logic and database.
	private static HashMap<Integer, School> schools = new HashMap<Integer, School>();

	
	/**
	 * Creates a new School object with the given name and stores in the the Hashmap
	 * Assigns the school a unique school ID
	 * @param name the name of the new school
	 */
	public static void createSchool(String name) {
		School school = new School(name, countID);
		schools.put(countID, school);
		
		countID++;
	}
	
	/**
	 * Returns a School object with the given ID
	 * @param ID The ID used to find the School
	 * @return The School with the given IO
	 */
	public static School getSchool(int ID) {
		return schools.get(ID);
	}
}
