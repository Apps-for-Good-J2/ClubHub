package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class ClubDescriptionMember extends AppCompatActivity {


    List<UserData> members = new ArrayList<>();
    List<UserData> leaders = new ArrayList<>();
    String thisClubID;
    Club thisClub;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_description_member);

        Intent intent = getIntent();
        thisClubID = intent.getStringExtra("clubID");
        thisClub = ClubManager.getClub(thisClubID);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        setTextViews();
        setUpMemberList();
        setUpLeaderList();

    }



    public void leaveClubOnClick(View v){
        String currentUserID = currentUser.getUid();

        // Remove this student from club
        thisClub.deleteMemberFirebase(currentUserID);
        // Remove this club from student
        StudentManager.getStudent(currentUserID).removeClubFromMemberFirebase(thisClubID);

        // Directs the user somewhere else or to member page for this club
        Intent intent = new Intent(this, ClubHubStudent.class);
        startActivity(intent);
    }

    private void setTextViews(){

        TextView clubNameText = findViewById(R.id.clubName2);
        clubNameText.setText(thisClub.getName());

        TextView descriptionText = findViewById(R.id.descriptionFillInText2);
        descriptionText.setText(thisClub.getDescription());
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

        ArrayAdapter<UserData> membersAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, leaders);

        final ListView listView = findViewById(R.id.leaveLeaderList2);
        listView.setAdapter(membersAdapter);
    }


}