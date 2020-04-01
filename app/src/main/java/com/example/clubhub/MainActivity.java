package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void setCurrentUserName(View v){

        EditText nameText = findViewById(R.id.userEditText);
        String uID = nameText.getText().toString();

        // Placeholder for firebase unique ID
        UserManager.currentUserID = UserManager.countID;

        UserManager.createUser(uID);

        Log.v("MainActivity", "Added user with id " + UserManager.currentUserID + " and name " +
                UserManager.getUserData(UserManager.currentUserID).getName());
    }



    public void progressToSchool(View v){

        setCurrentUserName(v);
        Intent intent = new Intent(this, CreateSchoolActivity.class);
        startActivity(intent);
    }
}
