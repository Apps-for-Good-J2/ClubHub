package com.example.clubhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class CreateAccountActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    private final String TAG = "CreateAccount";

    private TextView emailTextView;
    private TextView passwordTextView;
    private TextView usernameTextView;

    private String newSchoolID;

    private Spinner schoolSpinner;
    private ArrayList<School> schools;

    private RadioGroup selectUserTypeRadioGroup;
    private boolean isStudent;
    private boolean isTeacher;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        emailTextView = findViewById(R.id.createEmailText);
        passwordTextView = findViewById(R.id.createPasswordText);
        usernameTextView = findViewById(R.id.createUsernameText);
        schoolSpinner = findViewById(R.id.selectSchoolSpinner);
        selectUserTypeRadioGroup = findViewById(R.id.createAccountRadioGroup);
        isStudent = false;
        isTeacher = false;


        mAuth = FirebaseAuth.getInstance();

        setupSchoolSpinner();
        setupRadioGroup();

    }

    /**
     * Helper method to initialize the school spinner
     */
    private void setupSchoolSpinner(){
        //region Used to set up the school spinner

        schools = new ArrayList<>(SchoolManager.getListOfAllSchools());

        ArrayAdapter<School> schoolAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, schools);
        schoolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schoolSpinner.setAdapter(schoolAdapter);
        schoolSpinner.setOnItemSelectedListener(this);

        //endregion End code for school spinner
    }

    /**
     * Helper method to initialize the select user type radio group
     */
    private void setupRadioGroup(){
        //region Used to set up the RadioGroup

        selectUserTypeRadioGroup.clearCheck();
        selectUserTypeRadioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton clickedButton = group.findViewById(checkedId);
                        if(checkedId == R.id.createAccountStudentRadio){
                            isStudent = true;
                            isTeacher = false;}
                        else if(checkedId == R.id.createAccountTeacherRadio){
                            isTeacher = true;
                            isStudent = false;}
                        else
                            Log.d("Create Account", "Something has gone wrong picking user type");
                    }
                }
        );

        //endregion
    }

    /**
     * Creates a new user with an email and password
     * @param email this users email
     * @param password this users password
     */
    private void createEmailAndPasswordAccount(String email, String password){

        if(!checkForInvalidInput()) return;

        // Copied from Firebase documentation
        Log.d("CreateAccount", "In create method");
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            if(user == null){
                                Log.d(TAG, "User is null?");
                            }

                            else if(isStudent){
                                createLocalStudentUserData(user);
                                onCreateSuccessStudent();
                            }

                            else if(isTeacher){
                                createLocalTeacherUserData(user);
                                onCreateSuccessTeacher();
                            }

                            else{
                                Log.d(TAG, "A problem has occurred in choosing between creating a teacher or student");
                            }


                        } else {
                            Log.d(TAG, "Creating the user account was not successful");
                            Toast.makeText(getApplicationContext(),
                                    "Account creation unsuccessful. Perhaps one already exists with this email",
                                     Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }

    /**
     * Creates a student profile in firebase for storing club info
     * @param user the user just created
     */
    private void createLocalStudentUserData(FirebaseUser user){

        String name = usernameTextView.getText().toString();
        StudentManager.createStudent(name, user.getUid(), newSchoolID);

    }

    /**
     * Creates a teacher profile in firebase for storing club info
     * @param user the user just created
     */
    private void createLocalTeacherUserData(FirebaseUser user){
        String name = usernameTextView.getText().toString();
        TeacherManager.createTeacher(name, user.getUid(), newSchoolID);
    }

    /**
     * Checks that all info input by the user is valid
     * @return true if all user info is valid, false otherwise
     */
    private boolean checkForInvalidInput(){
        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String name = usernameTextView.getText().toString();
        String school = schools.toString(); //this is likely not right; this should be the selected school from the spinner

                ;

        if(email.isEmpty() || password.isEmpty() || name.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill in all fields", Toast.LENGTH_LONG).show();
            return false;
        }


        if(!email.contains("@")){
            Toast.makeText(getApplicationContext(),"Please enter a valid email", Toast.LENGTH_LONG).show();
            return false;
        }

        if(!isStudent && !isTeacher){
            Toast.makeText(getApplicationContext(),"Please select Student or Teacher", Toast.LENGTH_LONG).show();
            return false;
        }

        if (school.trim().isEmpty()|| )



        // add more checks?

        return true;

    }

    /**
     * Brings the newly created student to the ClubHub student page
     */
    private void onCreateSuccessStudent(){

        // check for if student or teacher
        Intent intent = new Intent(this, ClubHubStudentActivity.class);
        startActivity(intent);
    }

    /**
     * Brings the newly created teacher to the ClubHub teacher page
     */
    private void onCreateSuccessTeacher(){
        // Go to teacher page
        Log.d(TAG, "Teacher created (from onCreateSuccessTeacher");
        Intent intent = new Intent(this, ClubHubTeacherActivity.class);
        startActivity(intent);
    }

    /**
     * Button onClick method to create a new account
     */
    public void createAccountOnClick(View v){

        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        createEmailAndPasswordAccount(email, password);

    }

    /**
     * Brings the user to a page to add a new school to firebase
     */
    public void createNewSchoolOnClick(View v){
        Intent intent = new Intent(this, CreateSchoolActivity.class);
        startActivity(intent);
    }

    //region Performing action onItemSelected and onNothing selected

    /**
     * Assigns the school selected in the spinner to this users school
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View arg1, int position, long id) {
        newSchoolID = schools.get(position).getID();
        Log.d(TAG, "New School ID assigned");
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        Log.d(TAG, "New School ID NOT assigned");
    }
    //endregion



}
