package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class ClubHubTeacherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_hub_teacher);
    }

    /**
     * Sends the user to FindAClubActivity
     */
    public void goToClubFinder(View v){
        Intent intent = new Intent(this, FindAClubActivity.class);
        startActivity(intent);
    }

    /**
     * Sends the user to YourClubsTeacherActivity
     */
    public void goToClubList(View v){
        Intent intent = new Intent(this, YourClubsTeacherActivity.class);
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
