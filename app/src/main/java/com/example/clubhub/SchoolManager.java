package com.example.clubhub;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.HashMap;

public class SchoolManager extends AppCompatActivity {
	
	// Placeholder variable for the IDs given by firebase
	public static int countID = 0;

	public static String currentSchoolID;

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
		// Placeholder for user info
		currentSchoolID = key;
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

}
