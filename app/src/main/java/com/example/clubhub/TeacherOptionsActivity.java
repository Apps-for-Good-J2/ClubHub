package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TeacherOptionsActivity extends AppCompatActivity {

    private String thisClubID;
    private Club thisClub;

    private Student selectedLeader;
    private ArrayList<Student> leaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_options);
        Intent intent = getIntent();
        thisClubID = intent.getStringExtra("clubID");
        thisClub = ClubManager.getClub(thisClubID);

        selectedLeader = null;

        initiateLeaders();
    }

    private void initiateLeaders(){

        leaders = new ArrayList<>();


        for(String leaderRef : thisClub.getlIDs()){
            leaders.add(StudentManager.getStudent(leaderRef));
        }

        ArrayAdapter<Student> membersAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, leaders);

        final ListView listView = findViewById(R.id.manageLeadersListView);
        listView.setAdapter(membersAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedLeader = (leaders.get(position));

                TextView currentMemberDisplay = findViewById(R.id.currentlyViewedLeaderDisplay);
                currentMemberDisplay.setText("Currently managing " + selectedLeader.getName());

            }
        });

    }

    public void removeLeaderFromClub(View v){
        if(selectedLeader == null) return;
        if(checkNumLeaders()) return;
        thisClub.deleteLeaderFirebase(selectedLeader.getID());
        selectedLeader.removeClubFromLeaderFirebase(thisClubID);
        initiateLeaders();

    }

    public void revertLeaderToMember(View v){
        if(selectedLeader == null) return;
        if(checkNumLeaders()) return;
        thisClub.addMemberFirebase(selectedLeader.getID());
        selectedLeader.addUserToClubAsMemberFirebase(thisClubID);
        removeLeaderFromClub(v);
    }

    private boolean checkNumLeaders(){
        if(thisClub.getlIDs().size() == 1){
            Toast.makeText(this, "You need at least 1 student leader", Toast.LENGTH_SHORT).show();
        }
        return thisClub.getlIDs().size() == 1;
    }
}
