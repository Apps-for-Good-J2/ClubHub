package com.example.clubhub;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class to model the functionality of a school
 * that has clubs
 */
public class School extends DatabaseObject {

	public static final String SCHOOL_PATH = "schools";

	private String name;

	// List of reference IDs of this school's clubs
	private ArrayList<String> clubs;
	// ArrayList of students? Necessary?
	// ArrayList of teachers?

	/**
	 * Default constructor for the School class
	 */
	public School(){
		super();
		this.name = "";
		this.clubs = new ArrayList<String>();
	}

	/**
	 * Constructs a School object with a given name and ID
	 * @param name
	 * @param ID
	 */
	public School (String name, String ID) {
		super(SCHOOL_PATH, ID);
		this.name = name;
		this.clubs = new ArrayList<String>();
	
	}

	//region Methods that access the firebase database

	/**
	 * Adds a club with the given ID to this schools list of clubs in the database
	 * @param clubID the ID of the club to add
	 */
	public void addClubFirebase(String clubID){
		clubs.add(clubID);
		updateObjectDatabase();
	}

	/**
	 * Removes a given club from this school
	 * @param clubID the ID of the club to remove
	 */
	public void removeClubFirebase(String clubID){
		clubs.remove(clubID);
		updateObjectDatabase();
	}

	//endregion

	//region Getters and setters
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the iD
	 */
	public String getID() {
		return super.getFirebaseID();
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		super.setFirebaseID(iD);
	}

	@Override
	public String toString() {
		return name;
	}

	/**
	 * @return the clubs
	 */
	public ArrayList<String> getClubs() {
		return clubs;
	}
	/**
	 * @param clubs the clubs to set
	 */
	public void setClubs(ArrayList<String> clubs) {
		this.clubs = clubs;
	}

	//endregion

}
