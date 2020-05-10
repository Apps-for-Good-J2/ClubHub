package com.example.clubhub;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class CreateClubActivity extends AppCompatActivity {

    private EditText descriptionText;
    private EditText nameText;

    private FirebaseUser currentUser;
    private Spinner beginTimeSpinner;
    private Spinner endingTimeSpinner;
    private ArrayList<String> beginTimes;
    private ArrayList<String> endTimes;

    private String beginningTime = "";
    private String endingTime = "";

    private final String BEGIN_TIME_TEXT = "Select beginning time";
    private final String END_TIME_TEXT = "Select ending time";

    private CheckBox mon, tues, wed, thurs, fri, sat, sun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_club);

        descriptionText = findViewById(R.id.fillClubDesc);
        nameText = findViewById(R.id.startClubName);


        //region Spinner init

        //region Used to set up the beginning time spinner

        Time startTime = new Time(7,0);
        Time endTime = new Time(21,0);

        beginTimeSpinner = findViewById(R.id.timeSpinner);
        beginTimes = Time.giveListTimesBetween(startTime, endTime);
        beginTimes.add(0, BEGIN_TIME_TEXT);

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, beginTimes);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        beginTimeSpinner.setAdapter(timeAdapter);
        beginTimeSpinner.setOnItemSelectedListener(new BeginningTimeSpinner());

        //endregion

        //region Used to set up the ending time spinner

        startTime = new Time(7,0);
        endTime = new Time(21,0);

        endingTimeSpinner = findViewById(R.id.endingTimeSpinner);
        endTimes = Time.giveListTimesBetween(startTime, endTime);
        endTimes.add(0, END_TIME_TEXT);

        ArrayAdapter<String> timeAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, endTimes);
        timeAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endingTimeSpinner.setAdapter(timeAdapter2);
        endingTimeSpinner.setOnItemSelectedListener(new EndingTimeSpinner());

        //endregion

        //endregion

        //region CheckBox init
        mon = findViewById(R.id.monday);
        tues = findViewById(R.id.tuesday);
        wed = findViewById(R.id.wednesday);
        thurs = findViewById(R.id.thursday);
        fri = findViewById(R.id.friday);
        sat = findViewById(R.id.saturday);
        sun = findViewById(R.id.sunday);
        // endregion

    }


    /**
     * Checks input fields for invalid input
     * @return true is all input if valid, false if otherwise
     */
    private boolean checkForInvalidInput() {
        if(nameText.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please enter a club name", Toast.LENGTH_LONG).show();
            return false;
        }

        else if(descriptionText.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please enter a club description", Toast.LENGTH_LONG).show();
            return false;
        }

        else if(beginningTime.equals("") || endingTime.equals("")){
            Toast.makeText(getApplicationContext(), "Please enter club times", Toast.LENGTH_LONG).show();
            return false;
        }

        else if (getSelectedDays().size() == 0){
            Toast.makeText(getApplicationContext(), "Please select meeting days", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;

    }

    /**
     * Creates a new club with the data in the input fields
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createClub(){

        if(!checkForInvalidInput()) return;

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String name = nameText.getText().toString();
        String description = descriptionText.getText().toString();
        MeetingInfo meetingInfo = createMeetingInfo();
        ClubManager.createClub(name, description, meetingInfo);

        Intent intent = new Intent(this, ClubHubStudentActivity.class);
        startActivity(intent);
    }

    /**
     * Creates a meetingInfo object for the club from the data in the input fields
     * @return the new meetingInfo
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private MeetingInfo createMeetingInfo(){
        ArrayList<String> days = getSelectedDays();
        return new MeetingInfo(days, beginningTime, endingTime);
    }

    /**
     * Returns an ArrayList of the days selected as meetingDays with
     * the checkboxes
     * @return an ArrayList of the days selected as meetingDays
     */
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
     * On click method to initiate club creation
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void createClubOnClick(View v){
        createClub();
    }

    //region Spinner classes

    /**
     * Spinner helper for selecting the beginning time
     */
    class BeginningTimeSpinner implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(!beginTimes.get(position).equals(BEGIN_TIME_TEXT))
                beginningTime = beginTimes.get(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    /**
     * Spinner helper for selecting the ending time
     */
    class EndingTimeSpinner implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(!endTimes.get(position).equals(END_TIME_TEXT))
                endingTime = endTimes.get(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
    //endregion
}
