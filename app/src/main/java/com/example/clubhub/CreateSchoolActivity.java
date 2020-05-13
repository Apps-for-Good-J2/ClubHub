package com.example.clubhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateSchoolActivity extends AppCompatActivity {

    EditText nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_school);
        nameText = findViewById(R.id.schoolEditText);

    }


    /**
     * Creates a new school from the info in the input fields
     */
    public void createSchool(View v){
        if(!checkForInvalidInput()) return;

            String name = nameText.getText().toString();
            SchoolManager.createSchool(name);
            backToCreateAccount(v);

    }


    /**
     * Checks input fields for invalid input
     * @return true is all input if valid, false if otherwise
     */
    private boolean checkForInvalidInput() {
        if (nameText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter a valid name", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }

    /**
     * Brings the user back to creating an account
     */
        public void backToCreateAccount(View v){
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }
}
