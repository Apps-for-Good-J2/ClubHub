package com.example.clubhub;

import java.util.ArrayList;

public class Club {

	
	private String numID;
	private String name;
	private String description;
	private ArrayList<String> mIDs;
	private ArrayList<String> lIDs;
	private String schoolID;

	public Club(){
	    numID = "";
	    name = "";
	    description = "";
        this.mIDs = new ArrayList<>();
        this.lIDs = new ArrayList<>();
        schoolID = "";
    }
	

	public Club(String name, String ID, String schoolID, String creatorID) {
		super();
		this.numID = ID;
		this.name = name;
		this.description = "";
		this.mIDs = new ArrayList<>();
		this.lIDs = new ArrayList<>();
		lIDs.add(creatorID);
		this.schoolID = schoolID;
	}


	/**
	 * Adds a given user reference ID as a leader of this club
	 * @param ID
	 */
	public void addLeader(String ID) {
		lIDs.add(ID);
	}

	/**
	 * Adds a given user reference ID as a member of this club
	 * @param ID
	 */
	public void addMember(String ID) {
		mIDs.add(ID);
	}

	/**
	 * Checks if a giver user ID is a leader of this club
	 * @param ID
	 * @return
	 */
	public boolean isLeader(String ID) {
        return lIDs.contains(ID);
    }

	/**
	 * Checks if a given user ID is a member of this club
	 * @param ID
	 * @return
	 */
	public boolean isMember(String ID) {
        return mIDs.contains(ID);
    }
	
	
	
	
	
	


	/**
	 * @return the numID
	 */
	public String getNumID() {
		return numID;
	}

	/**
	 * @param numID the numID to set
	 */
	public void setNumID(String numID) {
		this.numID = numID;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the mIDs
	 */
	public ArrayList<String> getmIDs() {
		return mIDs;
	}

	/**
	 * @param mIDs the mIDs to set
	 */
	public void setmIDs(ArrayList<String> mIDs) {
		this.mIDs = mIDs;
	}

	/**
	 * @return the lIDs
	 */
	public ArrayList<String> getlIDs() {
		return lIDs;
	}

	/**
	 * @param lIDs the lIDs to set
	 */
	public void setlIDs(ArrayList<String> lIDs) {
		this.lIDs = lIDs;
	}


	@Override
	public String toString() {
		return "Club [name=" + name + ", description=" + description + " , ID=" + numID + "]";
	}



	/**
	 * @return the schoolID
	 */
	public String getSchoolID() {
		return schoolID;
	}



	/**
	 * @param schoolID the schoolID to set
	 */
	public void setSchoolID(String schoolID) {
		this.schoolID = schoolID;
	}

	
	
}
