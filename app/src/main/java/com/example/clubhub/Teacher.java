package com.example.clubhub;

import java.util.ArrayList;

public class Teacher extends UserData {

    private ArrayList<String> advisingClubs;

    public Teacher(){
        super();
        advisingClubs = new ArrayList<>();
    }

    public Teacher(String name, String iD, String schoolID) {
        super(name, iD, schoolID);
        advisingClubs = new ArrayList<>();
    }


    public ArrayList<String> getAdvisingClubs() {
        return advisingClubs;
    }

    public void setAdvisingClubs(ArrayList<String> advisingClubs) {
        this.advisingClubs = advisingClubs;
    }


}
