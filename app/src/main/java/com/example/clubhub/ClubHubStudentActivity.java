package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class ClubHubStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_hub_student);
    }

    /**
     * Sends the user to FindAClubActivity
     */
    public void goToClubFinder(View v){
        Intent intent = new Intent(this, FindAClubActivity.class);
        startActivity(intent);
    }

    /**
     * Sends the user to CreateClubActivity
     */
    public void goToClubCreator(View v){
        Intent intent = new Intent(this, CreateClubActivity.class);
        startActivity(intent);
    }

    /**
     * Sends the user to YourClubsStudentActivity
     */
    public void goToClubList(View v){
        Intent intent = new Intent(this, YourClubsStudentActivity.class);
        startActivity(intent);
    }

    /**
     * Signs this user out and kicks to opening
     */
    public void SignOut(View v){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, OpeningPageActivity.class);
        startActivity(intent);
    }
}
