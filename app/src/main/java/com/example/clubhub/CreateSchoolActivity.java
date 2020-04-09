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

public class CreateSchoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_school);

    }



    public void createSchool(){
        EditText nameText = findViewById(R.id.schoolEditText);
        String name = nameText.getText().toString();
        SchoolManager.createSchool(name);

    }

    public void backToCreateAccount(View v){
        createSchool();
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }
}
