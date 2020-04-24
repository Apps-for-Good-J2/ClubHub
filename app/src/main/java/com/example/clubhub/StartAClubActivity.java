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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class StartAClubActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_start_a_club);

        descriptionText = findViewById(R.id.fillClubDesc);
        nameText = findViewById(R.id.startClubName);


        //region Spinner init

        //region Used to set up the beginning time spinner

        beginTimeSpinner = findViewById(R.id.timeSpinner);
        beginTimes = generateAllTimes();
        beginTimes.add(0, BEGIN_TIME_TEXT);

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, beginTimes);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        beginTimeSpinner.setAdapter(timeAdapter);
        beginTimeSpinner.setOnItemSelectedListener(new BeginningTimeSpinner());

        //endregion

        //region Used to set up the ending time spinner

        endingTimeSpinner = findViewById(R.id.endingTimeSpinner);
        endTimes = generateAllTimes();
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
     * Helper class to generate all time spearated by 15 minutes in military time
     * Uses classes developed by Amrita T, although I wrote this method myself first
     * @return
     */
    private ArrayList<String> generateAllTimes(){
        Time time = new Time(0,0);

        ArrayList<String> allTimes = new ArrayList<String>();

        do {
            allTimes.add(new Time(time).toString());
            time.increment();
        }
        while(!time.equals(new Time(0,0)));

        return allTimes;
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createClub(){
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String name = nameText.getText().toString();
        String description = descriptionText.getText().toString();
        MeetingInfo meetingInfo = createMeetingInfo();
        ClubManager.createClub(name, description, meetingInfo);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private MeetingInfo createMeetingInfo(){
        ArrayList<String> days = getSelectedDays();
        return new MeetingInfo(days, beginningTime, endingTime);
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void createClubOnClick(View v){
        createClub();
        Intent intent = new Intent(this, ClubHubStudent.class);
        startActivity(intent);
    }


//region Spinner classes
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
