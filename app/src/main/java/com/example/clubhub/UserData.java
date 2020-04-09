package com.example.clubhub;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserData {

	private String name;
	private String ID;
	private ArrayList<String> mClubs;
	private ArrayList<String> lClubs;
	// Implement
	private String schoolID;

	public UserData(){
		this.name = "";
		ID = "";
		this.mClubs = new ArrayList<>();
		this.lClubs = new ArrayList<>();
		schoolID = "";
	}
	
	public UserData(String name, String iD, String schoolID) {
		super();
		this.name = name;
		ID = iD;
		this.mClubs = new ArrayList<>();
		this.lClubs = new ArrayList<>();
		this.schoolID = schoolID;
	}

	//region Methods that access firebase database

	/**
	 * Adds a given club reference ID to this users list of member clubs
	 * @param clubID
	 */
	public void addUserToClubAsMemberFirebase(String clubID) {
		this.mClubs.add(clubID);
		DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
		usersRef.child(this.ID).child("mClubs").setValue(mClubs);
	}

	/**
	 * Adds a given club reference ID to this users list of leader clubs
	 * @param clubID
	 */
	public void addUserToClubAsLeaderFirebase(String clubID) {

		this.lClubs.add(clubID);
		DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
		usersRef.child(this.ID).child("lClubs").setValue(lClubs);
	}

	public void removeClubFromLeaderFirebase(String clubID){
		//TODO access database and delete club from the leader's list
	}

	public void removeClubFromMemberFirebase(String clubID){
		//TODO access database and delete club from the member's list
	}

	//endregion

	public boolean isPartOfClub(String clubID){
		return mClubs.contains(clubID) || lClubs.contains(clubID);
	}


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
	/**
	 * @return the mClubs
	 */
	public ArrayList<String> getmClubs() {
		return mClubs;
	}
	/**
	 * @param mClubs the mClubs to set
	 */
	public void setmClubs(ArrayList<String> mClubs) {
		this.mClubs = mClubs;
	}
	/**
	 * @return the lClubs
	 */
	public ArrayList<String> getlClubs() {
		return lClubs;
	}
	/**
	 * @param lClubs the lClubs to set
	 */
	public void setlClubs(ArrayList<String> lClubs) {
		this.lClubs = lClubs;
	}


	public String getSchoolID() {
		return schoolID;
	}

	public void setSchoolID(String schoolID) {
		this.schoolID = schoolID;
	}

	//endregion

	@Override
	public String toString(){
		return name;
	}
}
