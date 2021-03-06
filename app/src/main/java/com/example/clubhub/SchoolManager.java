package com.example.clubhub;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Collection;
import java.util.HashMap;

/**
 * Helper class to manager School objects to create and get
 * School objects from firebase
 */
public class SchoolManager{


	private static HashMap<String, School> schools = new HashMap<>();

	/**
	 * Creates a new School object and stores it in the database with
	 * a random, unique key
	 * Assigns the school a unique school ID
	 * @param name the name of the new school
	 */
	public static void createSchool(String name) {

		DatabaseReference schoolsRef = FirebaseDatabase.getInstance().getReference(School.SCHOOL_PATH).push();
		String key = schoolsRef.getKey();
		schoolsRef.setValue(new School(name, key));
	}
	
	/**
	 * Returns a School object with the given ID
	 * @param ID The ID used to find the School
	 * @return The School with the given IO
	 */
	public static School getSchool(String ID) {

		return schools.get(ID);
	}

	/**
	 * Puts a school into the hashMap of schools, only to be called in OpeningPageActivity
	 * @param ID the ID of the school
	 * @param s the School object to add
	 */
	public static void putSchool(String ID, School s){
		schools.put(ID, s);
	}

	/**
	 * @return a Collection of all the Schools
	 */
	public static Collection<School> getListOfAllSchools(){
		return schools.values();
	}



}
