package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class CreateClubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_club);
    }


    public void setClubName(View v){


        EditText nameText = findViewById(R.id.clubEditText);
        String uID = nameText.getText().toString();

        // Find some way to effectively store what school the user is currently in
        ClubManager.currentClubID = ClubManager.countID;

        ClubManager.createClub(uID, SchoolManager.currentSchoolID);

        Log.v("MainActivity", "Added club with id " + ClubManager.currentClubID + " and name " +
                ClubManager.getClub(ClubManager.currentClubID).getName());
    }

    public void progressToDisplay(View v){

        setClubName(v);
        Intent intent = new Intent(this, TempDisplayActivity.class);
        startActivity(intent);
    }
}
