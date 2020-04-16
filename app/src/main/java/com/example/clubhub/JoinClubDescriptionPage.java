package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class JoinClubDescriptionPage extends AppCompatActivity{

    List<UserData> members = new ArrayList<>();
    List<UserData> leaders = new ArrayList<>();
    String thisClubID;
    Club thisClub;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_club_description_page);

        Intent intent = getIntent();
        thisClubID = intent.getStringExtra("clubID");
        thisClub = ClubManager.getClub(thisClubID);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        setTextViews();
        setUpMemberList();
        setUpLeaderList();

    }



    public void JoinClubOnClick(View v){
        String currentUserID = currentUser.getUid();

        if(thisClub.isLeader(currentUserID) || thisClub.isMember(currentUserID))
            return;

        StudentManager.getStudent(currentUserID).addUserToClubAsMemberFirebase(thisClubID);
        thisClub.addMemberFirebase(currentUserID);

        // Directs the user somewhere else or to member page for this club
        Intent intent = new Intent(this, ClubHubStudent.class);
        startActivity(intent);
    }

    private void setTextViews(){

        TextView clubNameText = findViewById(R.id.clubName1);
        clubNameText.setText(thisClub.getName());

        TextView descriptionText = findViewById(R.id.descriptionFillInText1);
        descriptionText.setText(thisClub.getDescription());
    }

    private void setUpMemberList(){

        for(String memberRef : thisClub.getmIDs()){
            members.add(StudentManager.getStudent(memberRef));
        }

        ArrayAdapter<UserData> membersAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, members);

        final ListView listView = findViewById(R.id.membersListView1);
        listView.setAdapter(membersAdapter);

    }

    private void setUpLeaderList() {
        for(String memberRef : thisClub.getlIDs()){
            leaders.add(StudentManager.getStudent(memberRef));
        }

        ArrayAdapter<UserData> membersAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, leaders);

        final ListView listView = findViewById(R.id.joinLeaderList1);
        listView.setAdapter(membersAdapter);
    }


}
