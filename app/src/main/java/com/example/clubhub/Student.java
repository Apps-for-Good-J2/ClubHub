package com.example.clubhub;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Student extends UserData {
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
        super(name, iD, schoolID);
        this.mClubs = new ArrayList<>();
        this.lClubs = new ArrayList<>();

    }

    public boolean isPartOfClub(String clubID){
        return mClubs.contains(clubID) || lClubs.contains(clubID);
    }

    //region Methods that access firebase database

    /**
     * Adds a given club reference ID to this users list of member clubs
     * @param clubID
     */
    public void addUserToClubAsMemberFirebase(String clubID) {
        this.mClubs.add(clubID);
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("students");
        usersRef.child(super.getID()).child("mClubs").setValue(mClubs);
    }

    /**
     * Adds a given club reference ID to this users list of leader clubs
     * @param clubID
     */
    public void addUserToClubAsLeaderFirebase(String clubID) {

        this.lClubs.add(clubID);
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("students");
        usersRef.child(super.getID()).child("lClubs").setValue(lClubs);
    }

    public void removeClubFromLeaderFirebase(String clubID){
        //TODO access database and delete club from the leader's list
    }

    public void removeClubFromMemberFirebase(String clubID){
        //TODO access database and delete club from the member's list
    }

    //endregion

    //region Getters and setters

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
