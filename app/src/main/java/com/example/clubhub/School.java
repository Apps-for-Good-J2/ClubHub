package com.example.clubhub;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class School {

	private String name;
	private String ID;
	private ArrayList<String> clubs;
	// ArrayList of students? Necessary?

	public School(){
		this.name = "";
		this.ID = "";
		this.clubs = new ArrayList<String>();
	}
	
	public School (String name, String ID) {
		this.name = name;
		this.ID = ID;
		this.clubs = new ArrayList<String>();
	
	}

	//region Methods that access the firebase database

	/**
	 * Adds a club with the given ID to this schools list of clubs in the database
	 * @param clubID the ID of the club to add
	 */
	public void addClubFirebase(String clubID){
		clubs.add(clubID);
		DatabaseReference clubsRef = FirebaseDatabase.getInstance().getReference("schools");
		clubsRef.child(ID).child("clubs").setValue(clubs);
	}

	public void removeClubFirebase(String clubID){
		//TODO access database and remove club from school
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
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
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
