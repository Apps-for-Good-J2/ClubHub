package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class CreateSchoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_school);

    }


    public void setSchoolName(View v){


        EditText nameText = findViewById(R.id.schoolEditText);
        String uID = nameText.getText().toString();


        // Find some way to effectively store what school the user is currently in
        SchoolManager.currentSchoolID = SchoolManager.countID;

        SchoolManager.createSchool(uID);

        Log.v("MainActivity", "Added school with id " + SchoolManager.currentSchoolID + " and name " +
                SchoolManager.getSchool(SchoolManager.currentSchoolID).getName());
    }

    public void progressToClub(View v){

        setSchoolName(v);
        Intent intent = new Intent(this, CreateClubActivity.class);
        startActivity(intent);
    }
}
