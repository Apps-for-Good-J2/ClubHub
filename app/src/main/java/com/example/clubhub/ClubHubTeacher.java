package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class ClubHubTeacher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_hub_teacher);
    }

    public void goToClubFinder(View v){
        Intent intent = new Intent(this, FindAClub.class);
        startActivity(intent);
    }

    public void goToClubList(View v){
        Intent intent = new Intent(this, YourClubsTeacher.class);
        startActivity(intent);
    }

    public void SignOut(View v){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, OpeningPage.class);
        startActivity(intent);
    }
}
