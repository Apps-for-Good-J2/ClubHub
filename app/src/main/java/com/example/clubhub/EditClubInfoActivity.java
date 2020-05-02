package com.example.clubhub;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class EditClubInfoActivity extends AppCompatActivity {

    private static final String TAG = "EditClub";

    EditText nameBox;
    EditText descBox;

    private CheckBox mon, tues, wed, thurs, fri, sat, sun;
    private ArrayList<CheckBox> dayBoxes;

    private String thisClubID;
    private Club thisClub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_club_info);

        Intent intent = getIntent();
        thisClubID = intent.getStringExtra("clubID");
        thisClub = ClubManager.getClub(thisClubID);

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

        initiateInformation();
    }

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void saveInformation(){

        thisClub.setName(nameBox.getText().toString());
        thisClub.setDescription(descBox.getText().toString());

        MeetingInfo meetingInfo = thisClub.getMeetingInfo();
        meetingInfo.addMeetingDaysFromArrayList(getSelectedDays());

        thisClub.updateObjectDatabase();

        Toast.makeText(this, "Club information updated",
                Toast.LENGTH_SHORT).show();
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

    private boolean checkForInvalidInput(){
        if(nameBox.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter a valid name", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(descBox.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter a valid description", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(getSelectedDays().size() == 0){
            Toast.makeText(this, "Please select meeting day(s)", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateClubOnClick(View v){

        if(!checkForInvalidInput()) return;
        saveInformation();

    }

    public void deleteClubOnClick(View v){
        ClubManager.deleteClub(thisClubID);

        Intent intent;

        if(StudentManager.getStudent(FirebaseAuth.getInstance().getCurrentUser().getUid()) != null)
            intent = new Intent(this, ClubHubStudent.class);
        else{
            intent = new Intent(this, ClubHubTeacher.class);
        }

        startActivity(intent);
    }

    public void goToManageMemberOnClick(View v){
        Intent intent = new Intent(this, ManageMembersActivity.class);
        intent.putExtra("clubID", thisClubID);
        startActivity(intent);
    }
}


