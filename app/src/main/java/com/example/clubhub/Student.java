package com.example.clubhub;


import java.util.ArrayList;

/**
 * Class to model a student that can lead and join clubs
 */
@SuppressWarnings({"WeakerAccess", "SpellCheckingInspection", "unused"})
public class Student extends UserData {

    public static final String STUDENT_PATH = "students";

    private ArrayList<String> mClubs; // Members of the club
    private ArrayList<String> lClubs; // Leaders of the club

    /**
     * Default constructor for the UserData class
     */
    public Student(){
        super();
        this.mClubs = new ArrayList<>();
        this.lClubs = new ArrayList<>();
    }

    /**
     * Constructs a UserData object given a name, ID, and schoolID
     * @param name the name of this UserData
     * @param iD the ID this UserData will be stored with in the database
     * @param schoolID the ID of the school this user is a part of
     */
    public Student(String name, String iD, String schoolID) {
        super(name, iD, schoolID, STUDENT_PATH);
        this.mClubs = new ArrayList<>();
        this.lClubs = new ArrayList<>();

    }

    /**
     * Checks if this Student is a part of a given club (member or leader)
     * @param clubID the club to see if this student is in
     * @return true in in the club, false otherwise
     */
    public boolean isPartOfClub(String clubID){
        return mClubs.contains(clubID) || lClubs.contains(clubID);
    }

    //region Methods that access firebase database

    /**
     * Adds a given club reference ID to this users list of member clubs
     * @param clubID the ID of the club to add
     */
    public void addUserToClubAsMemberFirebase(String clubID) {
        if (isPartOfClub(clubID)) return;
        this.mClubs.add(clubID);
        updateObjectDatabase();
    }

    /**
     * Adds a given club reference ID to this users list of leader clubs
     * @param clubID the ID of the club to add
     */
    public void addUserToClubAsLeaderFirebase(String clubID) {
        if(isPartOfClub(clubID)) return;
        this.lClubs.add(clubID);
        updateObjectDatabase();
    }

    /**
     * Removes a given club from this students leader list
     * @param clubID the ID of the club to remove
     */
    public void removeClubFromLeaderFirebase(String clubID){
        this.lClubs.remove(clubID);
        updateObjectDatabase();
    }

    /**
     * Removes a given club from this students member list
     * @param clubID the ID of the club to remove
     */
    public void removeClubFromMemberFirebase(String clubID){
        this.mClubs.remove(clubID);
        updateObjectDatabase();
    }

    //endregion

    //region Getters and setters

    /**
     * @return the member clubs
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

    //endregion


}
