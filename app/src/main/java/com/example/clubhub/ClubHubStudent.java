package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClubHubStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_hub_student);
    }

    public void goToClubFinder(View v){
        Intent intent = new Intent(this, FindAClub.class);
        startActivity(intent);
    }

    public void goToClubCreator(View v){
        Intent intent = new Intent(this, StartAClubActivity.class);
        startActivity(intent);
    }

    public void goToClubList(View v){
        // Implement
    }
}
