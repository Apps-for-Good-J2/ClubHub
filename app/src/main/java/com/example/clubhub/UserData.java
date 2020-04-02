package com.example.clubhub;

import android.util.Log;

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

	/**
	 * Adds a given club reference ID to this users list of member clubs
	 * @param ID
	 */
	public void addUserToClubAsMember(String ID) {
		this.mClubs.add(ID);
	}

	/**
	 * Adds a given club reference ID to this users list of leader clubs
	 * @param ID
	 */
	public void addUserToClubAsLeader(String ID) {
		this.lClubs.add(ID);
	}
	
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
}
