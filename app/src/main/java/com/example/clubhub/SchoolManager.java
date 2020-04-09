package com.example.clubhub;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class SchoolManager extends AppCompatActivity {


	private static HashMap<String, School> schools = new HashMap<>();

	private static School getSchoolSchool;

	
	/**
	 * Creates a new School object and stores it in the database with
	 * a random, unique key
	 * THIS WORKS
	 * Assigns the school a unique school ID
	 * @param name the name of the new school
	 */
	public static void createSchool(String name) {

		DatabaseReference schoolsRef = FirebaseDatabase.getInstance().getReference("schools").push();
		String key = schoolsRef.getKey();
		schoolsRef.setValue(new School(name, key));

	}
	
	/**
	 * Returns a School object with the given ID
	 * @param ID The ID used to find the School
	 * @return The School with the given IO
	 */
	public static School getSchool(String ID) {

		return schools.get(ID);
	}

	public static void putSchool(String ID, School s){
		schools.put(ID, s);
	}

	public static Collection<School> getListOfAllSchools(){
		return schools.values();
	}

	public static void addClubToCurrentSchool(String clubID){
		String currentSchoolID = UserManager.getUserData(FirebaseAuth.getInstance()
				.getCurrentUser().getUid()).getSchoolID();
		ArrayList<String> listOfClub = getSchool(currentSchoolID).getClubs();
		listOfClub.add(clubID);

		DatabaseReference clubsRef = FirebaseDatabase.getInstance().getReference("schools");
		clubsRef.child(currentSchoolID).child("clubs").setValue(listOfClub);
	}

}
