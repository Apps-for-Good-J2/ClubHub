package com.example.clubhub;

import java.util.ArrayList;

public class Club {

	
	private int numID;
	private String name;
	private String description;
	private ArrayList<Integer> mIDs;
	private ArrayList<Integer> lIDs;
	private int schoolID;
	
	/** @TODO Add constructors **/
	

	public Club(String name, int ID, int schoolID) {
		super();
		this.numID = ID;
		this.name = name;
		this.description = "";
		this.mIDs = new ArrayList<Integer>();
		this.lIDs = new ArrayList<Integer>();
		this.setSchoolID(schoolID);
	}
	


	public void addLeader(Integer ID) {
		lIDs.add(ID);
	}
	
	public void addMember(Integer ID) {
		mIDs.add(ID);
	}
	
	
	public boolean isLeader(Integer ID) {
		if(lIDs.contains(ID))
			return true;
		return false;
	}
	
	public boolean isMember(Integer ID) {
		if(mIDs.contains(ID))
			return true;
		return false;
	}
	
	
	
	
	
	


	/**
	 * @return the numID
	 */
	public int getNumID() {
		return numID;
	}

	/**
	 * @param numID the numID to set
	 */
	public void setNumID(int numID) {
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
	public ArrayList<Integer> getmIDs() {
		return mIDs;
	}

	/**
	 * @param mIDs the mIDs to set
	 */
	public void setmIDs(ArrayList<Integer> mIDs) {
		this.mIDs = mIDs;
	}

	/**
	 * @return the lIDs
	 */
	public ArrayList<Integer> getlIDs() {
		return lIDs;
	}

	/**
	 * @param lIDs the lIDs to set
	 */
	public void setlIDs(ArrayList<Integer> lIDs) {
		this.lIDs = lIDs;
	}


	@Override
	public String toString() {
		return "Club [name=" + name + ", description=" + description + " , ID=" + numID + "]";
	}



	/**
	 * @return the schoolID
	 */
	public int getSchoolID() {
		return schoolID;
	}



	/**
	 * @param schoolID the schoolID to set
	 */
	public void setSchoolID(int schoolID) {
		this.schoolID = schoolID;
	}

	
	
}
