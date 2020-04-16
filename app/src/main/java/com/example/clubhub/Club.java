package com.example.clubhub;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Class to model the functionality of a club
 * that has leaders and members
 */
public class Club {

	
	private String numID;
	private String name;
	private String description;
	private ArrayList<String> mIDs;
	private ArrayList<String> lIDs;
	private String schoolID;
	// Add in fields for meeting times

	/**
	 * Default constructor for the Club class
	 */
	public Club(){
	    numID = "";
	    name = "";
	    description = "";
        this.mIDs = new ArrayList<>();
        this.lIDs = new ArrayList<>();
        schoolID = "";
    }


	/**
	 * Constructors a Club object with a given name, ID, school ID, creator ID, and description
	 * @param name
	 * @param ID
	 * @param schoolID
	 * @param creatorID
	 * @param description
	 */
	public Club(String name, String ID, String schoolID, String creatorID, String description) {
		super();
		this.numID = ID;
		this.name = name;
		this.description = description;
		this.mIDs = new ArrayList<>();
		this.lIDs = new ArrayList<>();
		lIDs.add(creatorID);
		this.schoolID = schoolID;
	}


	/**
	 * Checks if a giver user ID is a leader of this club
	 * @param ID
	 * @return true if the user is a leader, false if not
	 */
	public boolean isLeader(String ID) {
        return lIDs.contains(ID);
    }

	/**
	 * Checks if a given user ID is a member of this club
	 * @param ID
	 * @return true if the user is a member, false if not
	 */
	public boolean isMember(String ID) {
        return mIDs.contains(ID);
    }



	//region Methods that access the firebase database

	/**
	 * Adds a given user reference ID as a leader of this club
	 * @param ID
	 */
	public void addLeaderFirebase(String ID) {
		lIDs.add(ID);
		DatabaseReference clubsRef = FirebaseDatabase.getInstance().getReference("clubs");
		clubsRef.child(this.numID).child("lIDs").setValue(lIDs);
	}

	/**
	 * Adds a given user reference ID as a member of this club
	 * @param ID
	 */
	public void addMemberFirebase(String ID) {
		mIDs.add(ID);
		DatabaseReference clubsRef = FirebaseDatabase.getInstance().getReference("clubs");
		clubsRef.child(this.numID).child("mIDs").setValue(mIDs);
	}

	public void deleteLeaderFirebase(String userID){
		lIDs.remove(userID);
		DatabaseReference clubsRef = FirebaseDatabase.getInstance().getReference("clubs");
		clubsRef.child(this.numID).child("lIDs").setValue(lIDs);
	}

	public void deleteMemberFirebase(String userID){
		mIDs.remove(userID);
		DatabaseReference clubsRef = FirebaseDatabase.getInstance().getReference("clubs");
		clubsRef.child(this.numID).child("mIDs").setValue(mIDs);
	}

	//endregion



	//region Getters and setters

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

	//endregion

	/**
	 * Returns the given name of this Club concatenated with
	 * " Club" when printed
	 * @return
	 */
	@Override
	public String toString() {
		return name + " Club";
	}




}
