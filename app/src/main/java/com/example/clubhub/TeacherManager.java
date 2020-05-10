package com.example.clubhub;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * Class to manage getting and setting teacher info
 */
public class TeacherManager {

    private static HashMap<String, Teacher> teachers  = new HashMap<>();

    /**
     * Creates a new Teacher object and stores it in the database with
     * a random, unique key
     * Assigns the user a school ID
     * @param name the name of the new user
     * @param userID the ID of the user in the auth system
     * @param schoolID the ID of the school this user belongs to
     */
    public static void createTeacher(String name, String userID, String schoolID) {

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference(Teacher.TEACHER_PATH).child(userID);
        usersRef.setValue(new Teacher(name, userID, schoolID));
    }

    /**
     * Returns a Teacher object with the given ID
     * @param ID The ID used to find the Teacher
     * @return The UserData with the given ID
     */
    public static Teacher getTeacher(String ID) {

        return teachers.get(ID);
    }

    /**
     * Puts a teacher into the TeacherManager's club HashMap
     * ONly to be used by the initiateUsers method
     * @param ID the ID of the teacher
     * @param s the Teacher object to add
     */
    public static void putTeacher(String ID, Teacher s){
        teachers.put(ID, s);
    }
}
