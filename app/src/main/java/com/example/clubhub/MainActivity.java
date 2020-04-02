package com.example.clubhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void setCurrentUserName(View v){

        EditText nameText = findViewById(R.id.userEditText);
        String uID = nameText.getText().toString();

        UserManager.createUser(uID, SchoolManager.currentSchoolID);
    }



    // Naming my be off during testing
    public void progressToSchool(View v){

        setCurrentUserName(v);
        Intent intent = new Intent(this, CreateClubActivity.class);
        startActivity(intent);
    }
}
