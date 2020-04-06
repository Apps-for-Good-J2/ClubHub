package com.example.clubhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class CreateAccountActivity extends AppCompatActivity {

    private TextView emailTextView;
    private TextView passwordTextView;
    private TextView usernameTextView;
    // Currently just creates a new school with given name
    private TextView schoolTextView;

    // Temp var
    private String newSchoolID;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        emailTextView = findViewById(R.id.createEmailText);
        passwordTextView = findViewById(R.id.createPasswordText);
        usernameTextView = findViewById(R.id.createUsernameText);
        schoolTextView = findViewById(R.id.createUserSchoolText);

        mAuth = FirebaseAuth.getInstance();
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

        // Temp line
        setSchool();

        String name = usernameTextView.getText().toString();
        UserManager.createUser(name, user.getUid(), newSchoolID);

        Log.d("CreateAccount", "New user created with name " + name + " and ID " + user.getUid());

    }

    // Temp method to stand for school spinner
    public void setSchool(){
        SchoolManager.createSchool(schoolTextView.getText().toString());
        newSchoolID = SchoolManager.currentSchoolID;

    }

    public void createAccountOnClick(View v){
        Log.v("Create Account", "In onClick");
        createEmailAndPasswordAccount();

    }

    private void onCreateSuccess(){
        Intent intent = new Intent(this, ClubHubStudent.class);
        startActivity(intent);
    }


    //region Initiate ArrayList database references - All of these must be in the first activity called

    @Override
    protected void onStart(){
        super.onStart();
        initiateSchools();
        initiateClubs();
        initiateUsers();

    }

    /**
     * Helper method to be called in the first activity
     * the user encounters in order to keep an updated list
     * of the users in the database
     */
    private void initiateUsers() {

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    UserManager.putUser(ds.getValue(UserData.class).getID(), ds.getValue(UserData.class));
                    Log.v("MainActivity", "Added USer: " + ds.getValue(UserData.class).getName() + " " + ds.getValue(UserData.class).getID());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    /**
     * Helper method to be called in the first activity
     * the user encounters in order to keep an updated list
     * of the clubs in the database
     */
    private void initiateClubs() {

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("clubs");
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    ClubManager.putClub(ds.getValue(Club.class).getNumID(), ds.getValue(Club.class));
                    Log.v("MainActivity", "Added Club: " + ds.getValue(Club.class).getName());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    /**
     * Helper method to be called in the first activity
     * the user encounters in order to keep an updated list
     * of the schools in the database
     */
    private void initiateSchools() {


        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("schools");
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    SchoolManager.putSchool(ds.getValue(School.class).getID(), ds.getValue(School.class));
                    Log.v("MainActivity", "Added School: " + ds.getValue(School.class).getName());


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    //endregion End first activity called section
}
