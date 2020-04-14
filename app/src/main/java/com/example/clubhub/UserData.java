package com.example.clubhub;


/**
 * Class to model the data of users that has
 * a school
 */
public class UserData {

	private String name;
	private String ID;
	private String schoolID;

	/**
	 * Default constructor for the UserData class
	 */
	public UserData(){
		this.name = "";
		ID = "";
		schoolID = "";
	}

	/**
	 * Constructs a UserData object given a name, ID, and schoolID
	 * @param name the name of this UserData
	 * @param iD the ID this UserData will be stored with in the database
	 * @param schoolID the ID of the school this user is a part of
	 */
	public UserData(String name, String iD, String schoolID) {
		super();
		this.name = name;
		ID = iD;
		this.schoolID = schoolID;
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
	 * Returns the name of the User when printed
	 * @return
	 */
	@Override
	public String toString(){
		return name;
	}
}
