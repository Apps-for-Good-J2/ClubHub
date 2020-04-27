package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class StudentClubDescription extends AppCompatActivity {

    private final String TAG = "ClubDescription";

    List<UserData> members = new ArrayList<>();
    List<UserData> leaders = new ArrayList<>();
    String thisClubID;
    Club thisClub;
    FirebaseUser currentUser;
    Button button;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_club_description);

        Intent intent = getIntent();
        thisClubID = intent.getStringExtra("clubID");
        thisClub = ClubManager.getClub(thisClubID);
        button = findViewById(R.id.clubDescButton);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        setTextViews();
        setUpMemberList();
        setUpLeaderList();
        setUpDayList();


        if(thisClub.isMember(currentUser.getUid())){
            button.setOnClickListener(new leaveClubOnClickListener());
            button.setText("LEAVE CLUB");
        }

        else if(thisClub.isLeader(currentUser.getUid())){
            button.setOnClickListener(new editClubOnClickListener());
            button.setText("EDIT CLUB INFO");
        }

        else {
            button.setOnClickListener(new joinClubOnClickListener());
            button.setText("JOIN CLUB");
        }

    }

    @SuppressLint("SetTextI18n")
    private void setTextViews(){

        TextView clubNameText = findViewById(R.id.clubName2);
        clubNameText.setText(thisClub.getName());

        TextView descriptionText = findViewById(R.id.descriptionFillInText2);
        descriptionText.setText(thisClub.getDescription());

        TextView teacherNameText = findViewById(R.id.leaveTeacherAdvisorText2);
        if(thisClub.hasTeacherAdviser()){
            teacherNameText.setText(TeacherManager.getTeacher(thisClub.getTeacherID()).toString());
        }
        else{
            teacherNameText.setText("This club does not have an adviser");
        }

        TextView timeText = findViewById(R.id.meetingTimeDisplay);
        timeText.setText("This club meets between " + thisClub.getMeetingInfo().getMeetingStartTime() + " and " +
                thisClub.getMeetingInfo().getMeetingEndTime() + " on:");

    }

    private void setUpMemberList(){

        for(String memberRef : thisClub.getmIDs()){
            members.add(StudentManager.getStudent(memberRef));
        }

        ArrayAdapter<UserData> membersAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, members);

        final ListView listView = findViewById(R.id.membersListView2);
        listView.setAdapter(membersAdapter);

    }

    private void setUpLeaderList() {
        for(String memberRef : thisClub.getlIDs()){
            leaders.add(StudentManager.getStudent(memberRef));
        }

        ArrayAdapter<UserData> leadersAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, leaders);

        final ListView listView = findViewById(R.id.leaveLeaderList2);
        listView.setAdapter(leadersAdapter);
    }

    private void setUpDayList() {

        ArrayAdapter<String> daysAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, thisClub.getMeetingInfo().getOnlyMeetingDays());

        final ListView listView = findViewById(R.id.meetingDayDisplay);
        listView.setAdapter(daysAdapter);
    }



    //region Button onClick Listeners

    private class joinClubOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String currentUserID = currentUser.getUid();

            if(thisClub.isLeader(currentUserID) || thisClub.isMember(currentUserID))
                return;

            StudentManager.getStudent(currentUserID).addUserToClubAsMemberFirebase(thisClubID);
            thisClub.addMemberFirebase(currentUserID);

            // Directs the user somewhere else or to member page for this club
            Intent intent = new Intent(StudentClubDescription.this, ClubHubStudent.class);
            startActivity(intent);
        }
    }

    private class leaveClubOnClickListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Log.d(TAG, "Leaving club");
            String currentUserID = currentUser.getUid();

            // Remove this student from club
            thisClub.deleteMemberFirebase(currentUserID);
            // Remove this club from student
            StudentManager.getStudent(currentUserID).removeClubFromMemberFirebase(thisClubID);

            // Directs the user somewhere else or to member page for this club
            Intent intent = new Intent(StudentClubDescription.this, ClubHubStudent.class);
            startActivity(intent);
        }
    }

    private class editClubOnClickListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(StudentClubDescription.this, EditClubInfoActivity.class);
            startActivity(intent);
        }
    }
    //endregion


}