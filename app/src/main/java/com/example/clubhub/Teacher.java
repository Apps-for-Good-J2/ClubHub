package com.example.clubhub;

import java.util.ArrayList;

/**
 * Class to model the data of a teacher adviser to clubs
 */
public class Teacher extends UserData {

    public static final String TEACHER_PATH = "teachers";

    private ArrayList<String> advisingClubs;

    /**
     * Default constructor for the teacher class
     * Sets advisingClubs as an empty ArrayList
     */
    public Teacher(){
        super();
        advisingClubs = new ArrayList<>();
    }

    /**
     * Creates a new teacher with a given name, ID, and schoolID
     * @param name the name of this teacher
     * @param iD the iD of this teacher
     * @param schoolID the iD of the school of this teacher
     */
    public Teacher(String name, String iD, String schoolID) {
        super(name, iD, schoolID, TEACHER_PATH);
        advisingClubs = new ArrayList<>();
    }

    public void addAdvisingClub(String clubID){
        advisingClubs.add(clubID);
        updateObjectDatabase();
    }

    public void removeAdvisingClub(String clubID){
        advisingClubs.remove(clubID);
        updateObjectDatabase();
    }


    /**
     * Get the advisingClubs
     * @return Arraylist of club references that this teacher advises
     */
    public ArrayList<String> getAdvisingClubs() {
        return advisingClubs;
    }

    /**
     * Sets advisingClubs to a given ArrayList
     * @param advisingClubs the new ArrayList of club references to set advising clubs to
     */
    public void setAdvisingClubs(ArrayList<String> advisingClubs) {
        this.advisingClubs = advisingClubs;
    }


}
