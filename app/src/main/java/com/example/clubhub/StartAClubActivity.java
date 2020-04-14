package com.example.clubhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartAClubActivity extends AppCompatActivity {

    private EditText descriptionText;
    private EditText nameText;

    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_a_club);

        descriptionText = findViewById(R.id.fillClubDesc);
        nameText = findViewById(R.id.startClubName);


    }



    private void createClub(){
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String name = nameText.getText().toString();
        String description = descriptionText.getText().toString();

        ClubManager.createClub(name, description);
    }

    public void createClubOnClick(View v){
        createClub();
        Intent intent = new Intent(this, ClubHubStudent.class);
        startActivity(intent);


    }

}
