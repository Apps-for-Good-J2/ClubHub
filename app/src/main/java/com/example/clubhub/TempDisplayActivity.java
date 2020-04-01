package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TempDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_display);
    }

    @Override
    protected void onStart(){
        super.onStart();

        TextView userName = findViewById(R.id.userName);
        String userNameText = UserManager.getUserData(UserManager.currentUserID).getName();
        userName.setText(userNameText);

        TextView schoolName = findViewById(R.id.schoolName);
        // Create SchoolManager.getCurrentSchool?
        String schoolNameText = SchoolManager.getSchool((SchoolManager.currentSchoolID)).getName();
        schoolName.setText(schoolNameText);

        TextView clubName = findViewById(R.id.clubName);
        String clubNameText = ClubManager.getClub(ClubManager.currentClubID).getName();
        clubName.setText(clubNameText);

    }
}
