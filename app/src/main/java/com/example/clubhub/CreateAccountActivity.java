package com.example.clubhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CreateAccountActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    private TextView emailTextView;
    private TextView passwordTextView;
    private TextView usernameTextView;

    private String newSchoolID;

    private Spinner schoolSpinner;
    private ArrayList<School> schools;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        emailTextView = findViewById(R.id.createEmailText);
        passwordTextView = findViewById(R.id.createPasswordText);
        usernameTextView = findViewById(R.id.createUsernameText);
        schoolSpinner = findViewById(R.id.selectSchoolSpinner);

        mAuth = FirebaseAuth.getInstance();


        //region Used to set up the school spinner

        schools = new ArrayList<>(SchoolManager.getListOfAllSchools());

        ArrayAdapter<School> schoolAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, schools);
        schoolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schoolSpinner.setAdapter(schoolAdapter);
        schoolSpinner.setOnItemSelectedListener(this);

        //endregion End code for school spinner


    }

    private void createEmailAndPasswordAccount(){

        // Check these are valid later
        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();

        // Copied from Firebase documentation
        Log.d("CreateAccount", "In create method");
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("CreateAccount", "onComplete worked");
                            FirebaseUser user = mAuth.getCurrentUser();
                            createLocalUserData(user);
                            onCreateSuccess();

                        } else {
                            Log.d("CreateAccount", "onComplete did NOT work");
                        }

                    }
                });

    }

    private void createLocalUserData(FirebaseUser user){

        String name = usernameTextView.getText().toString();
        UserManager.createUser(name, user.getUid(), newSchoolID);

    }


    public void createAccountOnClick(View v){
        Log.v("Create Account", "In onClick");
        createEmailAndPasswordAccount();

    }

    public void createNewSchoolOnClick(View v){
        Intent intent = new Intent(this, CreateSchoolActivity.class);
        startActivity(intent);
    }

    private void onCreateSuccess(){
        Intent intent = new Intent(this, ClubHubStudent.class);
        startActivity(intent);
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View arg1, int position, long id) {
        newSchoolID = schools.get(position).getID();
        Log.d("CreateAccount", "New School ID assigned");
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        Log.d("CreateAccount", "New School ID NOT assigned");
    }


}
