package com.example.clubhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OpeningPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_page);
    }

    /**
     * Sends the user to the login page
     */
    public void goToLogin(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Sends the user to the sign up page
     */
    public void goToSignUp(View v){
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    //region Initiate ArrayList database references - All of these must be in the first activity called

    @Override
    protected void onStart(){
        super.onStart();
        initiateSchools();
        initiateClubs();
        initiateStudents();
        initiateTeachers();

    }

    /**
     * Helper method to be called in the first activity
     * the user encounters in order to keep an updated list
     * of the students in the database
     */
    private void initiateStudents() {

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference(Student.STUDENT_PATH);
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    StudentManager.putStudent(ds.getValue(Student.class).getID(), ds.getValue(Student.class));
                    Log.v("MainActivity", "Added USer: " + ds.getValue(Student.class).getName() + " " + ds.getValue(Student.class).getID());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    /**
     * Helper method to be called in the first activity
     * the user encounters in order to keep an updated list
     * of the clubs in the database
     */
    private void initiateClubs() {

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference(Club.CLUB_PATH);
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    ClubManager.putClub(ds.getValue(Club.class).getNumID(), ds.getValue(Club.class));
                    Log.v("MainActivity", "Added Club: " + ds.getValue(Club.class).getName());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    /**
     * Helper method to be called in the first activity
     * the user encounters in order to keep an updated list
     * of the schools in the database
     */
    private void initiateSchools() {


        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference(School.SCHOOL_PATH);
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    SchoolManager.putSchool(ds.getValue(School.class).getID(), ds.getValue(School.class));
                    Log.v("MainActivity", "Added School: " + ds.getValue(School.class).getName());


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    /**
     * Helper method to be called in the first activity
     * the user encounters in order to keep an updated list
     * of the teachers in the database
     */
    private void initiateTeachers() {

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference(Teacher.TEACHER_PATH);
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    TeacherManager.putTeacher(ds.getValue(Teacher.class).getID(), ds.getValue(Teacher.class));
                    Log.v("MainActivity", "Added USer: " + ds.getValue(Teacher.class).getName() + " " + ds.getValue(Teacher.class).getID());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    //endregion End first activity called section


}
