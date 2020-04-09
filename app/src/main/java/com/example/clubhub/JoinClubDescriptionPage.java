package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class JoinClubDescriptionPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_club_description_page);

        Intent intent = getIntent();
        String thisClubID = intent.getStringExtra("clubID");
        Club thisClub = ClubManager.getClub(thisClubID);

        TextView clubNameText = findViewById(R.id.clubName);
        clubNameText.setText(thisClub.getName());

        TextView descriptionText = findViewById(R.id.descriptionFillInText);
        descriptionText.setText(thisClub.getDescription());
    }



}
