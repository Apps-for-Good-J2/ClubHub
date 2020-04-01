package com.example.clubhub;

import java.util.ArrayList;

public class UserData {

	private String name;
	private int ID;
	private ArrayList<Integer> mClubs;
	private ArrayList<Integer> lClubs;
	// Implement
	private int schoolID;
	
	public UserData(String name, int iD) {
		super();
		this.name = name;
		ID = iD;
		this.mClubs = new ArrayList<Integer>();
		this.lClubs = new ArrayList<Integer>();
	}

	public void addUserToClubAsMember(int ID, ClubManager manager) {
		manager.getClub(ID).addMember(this.ID);
		this.mClubs.add(ID);
	}
	
	public void addUserToClubAsLeader(int ID, ClubManager manager) {
		manager.getClub(ID).addLeader(this.ID);
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
	public int getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * @return the mClubs
	 */
	public ArrayList<Integer> getmClubs() {
		return mClubs;
	}
	/**
	 * @param mClubs the mClubs to set
	 */
	public void setmClubs(ArrayList<Integer> mClubs) {
		this.mClubs = mClubs;
	}
	/**
	 * @return the lClubs
	 */
	public ArrayList<Integer> getlClubs() {
		return lClubs;
	}
	/**
	 * @param lClubs the lClubs to set
	 */
	public void setlClubs(ArrayList<Integer> lClubs) {
		this.lClubs = lClubs;
	}
	
	
}
