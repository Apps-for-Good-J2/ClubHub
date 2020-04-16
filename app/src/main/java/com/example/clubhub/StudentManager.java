package com.example.clubhub;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collection;
import java.util.HashMap;

/**
 * Helper class to manager UserData objects to create and get
 * UserData objects from firebase
 */
public class StudentManager{

	private static HashMap<String, Student> students  = new HashMap<String, Student>();

	/**
	 * Creates a new User object and stores it in the database with
	 * a random, unique key
	 * Assigns the user a school ID
	 * @param name the name of the new user
	 * @param userID the ID of the user in the auth system
	 * @param schoolID the ID of the school this user belongs to
	 */
	public static void createStudent(String name, String userID, String schoolID) {

		DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("students").child(userID);
		usersRef.setValue(new Student(name, userID, schoolID));
	}
	
	/**
	 * Returns a Student object with the given ID
	 * @param ID The ID used to find the Student
	 * @return The Student with the given ID
	 */
	public static Student getStudent(String ID) {
		return students.get(ID);
	}

	/**
	 * Puts a user into the UserManager's club HashMap
	 * ONly to be used by the initiateUsers method
	 * @param ID
	 * @param s
	 */
	public static void putStudent(String ID, Student s){
		students.put(ID, s);
	}


	

}
