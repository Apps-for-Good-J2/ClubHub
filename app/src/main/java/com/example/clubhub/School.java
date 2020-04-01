package com.example.clubhub;

import java.util.ArrayList;

public class School {

	private String name;
	private int ID;
	private ArrayList<Integer> clubs;
	// ArrayList of students?
	
	public School (String name, int ID) {
		this.name = name;
		this.ID = ID;
		this.clubs = new ArrayList<Integer>();
	
	}

	
	public void addClub(Integer club) {
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
	 * @return the clubs
	 */
	public ArrayList<Integer> getClubs() {
		return clubs;
	}
	/**
	 * @param clubs the clubs to set
	 */
	public void setClubs(ArrayList<Integer> clubs) {
		this.clubs = clubs;
	}
	
	
}
