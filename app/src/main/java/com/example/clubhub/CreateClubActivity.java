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


    /** DONT USE **/

    public void setClubName(View v){

        EditText nameText = findViewById(R.id.clubEditText);
        String uID = nameText.getText().toString();
        ClubManager.getClub(uID);
    }

    public void progressToDisplay(View v){

        setClubName(v);
        Intent intent = new Intent(this, ClubHubStudent.class);
        startActivity(intent);
    }
}
