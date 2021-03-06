package com.example.clubhub;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class EditClubInfoActivity extends AppCompatActivity {

    EditText nameBox;
    EditText descBox;

    private CheckBox mon, tues, wed, thurs, fri, sat, sun;
    private ArrayList<CheckBox> dayBoxes;

    private String thisClubID;
    private Club thisClub;
    private String endingTime;
    private String beginningTime;

    private ArrayList<String> beginTimes;
    private ArrayList<String> endTimes;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_club_info);


        nameBox = findViewById(R.id.clubEditName);
        descBox = findViewById(R.id.clubEditDesc);


        mon = findViewById(R.id.monday2);
        tues = findViewById(R.id.tuesday2);
        wed = findViewById(R.id.wednesday2);
        thurs = findViewById(R.id.thursday2);
        fri = findViewById(R.id.friday2);
        sat = findViewById(R.id.saturday2);
        sun = findViewById(R.id.sunday2);
        dayBoxes = new ArrayList<>(Arrays.asList(mon, tues, wed, thurs, fri, sat, sun));


        Log.d("Edit", "ONCreate called");


    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        thisClubID = intent.getStringExtra("clubID");
        thisClub = ClubManager.getClub(thisClubID);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        initiateSpinners();
        initiateInformation();

        Log.d("Edit", "OnStart called");


        // Checks if user editing is a leader or teacher
        Button optionsButton = findViewById(R.id.optionsButton);

        assert currentUser != null;
        if(thisClub.isLeader(currentUser.getUid())){
            Log.d("Edit", "is leader passed called");
            optionsButton.setText("Leader Options");
            optionsButton.setOnClickListener(new goToLeaderOptionsOnClick());
        }
        else if(thisClub.getTeacherID().equals(currentUser.getUid())){
            Log.d("Edit", "is Teacher passed called");
            optionsButton.setText("Adviser Options");
            optionsButton.setOnClickListener(new goToTeacherOptionsOnClick());
        }
        else{
            Log.d("Edit", "nothing passed");
            noUserError();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private void noUserError() {
        Log.d("Edit", "noUserError called");
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        Intent intentBackup;
        assert currentUser != null;
        if(StudentManager.getStudent(currentUser.getUid()) != null){
            Log.d("Edit", "student error called");
            intentBackup = new Intent(this, ClubHubStudentActivity.class);
        }
        else{
            intentBackup = new Intent(this, ClubHubTeacherActivity.class);
        }
        startActivity(intentBackup);
    }


    /**
     * Sets the input fields to automatically contain the clubs current info
     */
    private void initiateInformation(){

        nameBox.setText(thisClub.getName());
        descBox.setText(thisClub.getDescription());

        HashMap<String, Boolean> dayStatus = thisClub.getMeetingInfo().getMeetingDays();
        int i = 0;
        for(String day : MeetingInfo.allDays){
            dayBoxes.get(i).setChecked(dayStatus.get(day));
            i++;
        }
    }

    /**
     * Sets the information in the input fields as the club's
     * new information
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void saveInformation(){

        thisClub.setName(nameBox.getText().toString());
        thisClub.setDescription(descBox.getText().toString());

        MeetingInfo meetingInfo = thisClub.getMeetingInfo();
        meetingInfo.addMeetingDaysFromArrayList(getSelectedDays());
        meetingInfo.setMeetingStartTime(beginningTime);
        meetingInfo.setMeetingEndTime(endingTime);

        thisClub.updateObjectDatabase();

        Toast.makeText(this, "Club information updated",
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Initiates the Spinners for selecting beginning and end times
     */
    private void initiateSpinners(){
        //region Spinner init

        //region Used to set up the beginning time spinner

        Time startTime = new Time(7,0);
        Time endTime = new Time(21,0);

        Spinner beginTimeSpinner = findViewById(R.id.editStartTimeSpinner);
        beginTimes = Time.giveListTimesBetween(startTime, endTime);
        beginTimes.add(0, thisClub.getMeetingInfo().getMeetingStartTime());

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, beginTimes);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        beginTimeSpinner.setAdapter(timeAdapter);
        beginTimeSpinner.setOnItemSelectedListener(new BeginningTimeSpinner());

        //endregion

        //region Used to set up the ending time spinner

        startTime = new Time(7,0);
        endTime = new Time(21,0);

        Spinner endingTimeSpinner = findViewById(R.id.editEndTimeSpinner);
        endTimes = Time.giveListTimesBetween(startTime, endTime);
        endTimes.add(0,  thisClub.getMeetingInfo().getMeetingEndTime());

        ArrayAdapter<String> timeAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, endTimes);
        timeAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endingTimeSpinner.setAdapter(timeAdapter2);
        endingTimeSpinner.setOnItemSelectedListener(new EndingTimeSpinner());
    }

    private ArrayList<String> getSelectedDays(){
        ArrayList<String> days = new ArrayList<>();
        if(mon.isChecked()) days.add(MeetingInfo.MONDAY_STR);
        if(tues.isChecked()) days.add(MeetingInfo.TUESDAY_STR);
        if(wed.isChecked()) days.add(MeetingInfo.WEDNESDAY_STR);
        if(thurs.isChecked()) days.add(MeetingInfo.THURSDAY_STR);
        if(fri.isChecked()) days.add(MeetingInfo.FRIDAY_STR);
        if(sat.isChecked()) days.add(MeetingInfo.SATURDAY_STR);
        if(sun.isChecked()) days.add(MeetingInfo.SUNDAY_STR);

        return days;
    }


    /**
     * Checks input fields for invalid input
     * @return true is all input if valid, false if otherwise
     */
    private boolean checkForInvalidInput(){
        if(nameBox.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter a valid name", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(descBox.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter a valid description", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(beginningTime.isEmpty()){
            Toast.makeText(this, "Please select a valid start time", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(endingTime.isEmpty()){
            Toast.makeText(this, "Please select a valid end time", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(getSelectedDays().size() == 0){
            Toast.makeText(this, "Please select meeting day(s)", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * OnCLick to save the club info currently in the input field to the database
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateClubOnClick(View v){

        if(!checkForInvalidInput()) return;
        saveInformation();

    }

    /**
     * Deletes a club from the database
     */
    public void deleteClubOnClick(View v){
        ClubManager.deleteClub(thisClubID);

        Intent intent;

        if(StudentManager.getStudent(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()) != null)
            intent = new Intent(this, ClubHubStudentActivity.class);
        else{
            intent = new Intent(this, ClubHubTeacherActivity.class);
        }

        startActivity(intent);
    }

    /**
     * OnClick to manageMembersActivity
     */
    public void goToManageMemberOnClick(View v){
        Intent intent = new Intent(this, ManageMembersActivity.class);
        intent.putExtra("clubID", thisClubID);
        startActivity(intent);
    }

    /**
     * OnClick to LeaderOptionsActivity
     */
    private class goToLeaderOptionsOnClick implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(EditClubInfoActivity.this, LeaderOptionsActivity.class);
            intent.putExtra("clubID", thisClubID);
            startActivity(intent);
        }
    }

    /**
     * OnClick to TeacherOptionActivity
     */
    private class goToTeacherOptionsOnClick implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(EditClubInfoActivity.this, TeacherOptionsActivity.class);
            intent.putExtra("clubID", thisClubID);
            startActivity(intent);
        }
    }

    // region Spinner classes

    /**
     * Spinner class for beginningTime
     */
    class BeginningTimeSpinner implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                beginningTime = beginTimes.get(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


    /**
     * Spinner class for ending time
     */
    class EndingTimeSpinner implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                endingTime = endTimes.get(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    // endregion
}


