package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TempDisplayActivity extends AppCompatActivity {

    private FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_display);
    }

    @Override
    protected void onStart(){
        super.onStart();

        TextView userName = findViewById(R.id.userName);
        String userNameText = UserManager.getUserData(mUser.getUid()).getName();
        userName.setText(userNameText);

        TextView schoolName = findViewById(R.id.schoolName);
        // Create SchoolManager.getCurrentSchool?
        //String schoolNameText = SchoolManager.getSchool((SchoolManager.currentSchoolID)).getName();
        //schoolName.setText(schoolNameText);

        TextView clubName = findViewById(R.id.clubName);
        // Shows that the school list of clubs is there, but not visible in firebase?
        String clubNameText = ClubManager.getClub(ClubManager.currentClubID).getName();
        clubName.setText(clubNameText);



    }
}
