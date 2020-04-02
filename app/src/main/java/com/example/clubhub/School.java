package com.example.clubhub;

import java.util.ArrayList;

public class School {

	private String name;
	private String ID;
	private ArrayList<String> clubs;
	// ArrayList of students?

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

	/**
	 * Adds a given club reference ID to this school
	 * @param club
	 */
	public void addClub(String club) {
		clubs.add(club);
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

	@Override
	public String toString() {
		return "School{" +
				"name='" + name + '\'' +
				'}';
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
	
	
}
