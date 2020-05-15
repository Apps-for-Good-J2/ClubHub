package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ManageMembersActivity extends AppCompatActivity {

    Club thisClub;
    String thisClubID;
    ArrayList<Student> members;

    Student selectedMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_members);

        Intent intent = getIntent();
        thisClubID = intent.getStringExtra("clubID");


        selectedMember = null;

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        thisClub = ClubManager.getClub(thisClubID);
        assert currentUser != null;
        if(thisClub.isLeader(currentUser.getUid())){
            Log.d("Edit", "is leader passed called");

        }
        else if(thisClub.getTeacherID().equals(currentUser.getUid())){
            Log.d("Edit", "is Teacher passed called");
        }
        else{
            Log.d("Edit", "nothing passed");
            noUserError();
        }


        setUpMemberList();
    }

    /**
     * Removes the selected member from the current club
     */
    public void removeSelectedMemberOnClick(View v){
        if(selectedMember == null) return;

        thisClub.deleteMemberFirebase(selectedMember.getID());
        selectedMember.removeClubFromMemberFirebase(thisClubID);
        selectedMember = null;
        TextView currentMemberDisplay = findViewById(R.id.currentlyViewedMemberDisplay);
        currentMemberDisplay.setText("");
        setUpMemberList();
    }

    /**
     * Makes the selected member a leader of the current club
     */
    public void makeMemberLeader(View v){
        if(selectedMember == null) return;
        thisClub.deleteMemberFirebase(selectedMember.getID());
        thisClub.addLeaderFirebase(selectedMember.getID());

        selectedMember.removeClubFromMemberFirebase(thisClubID);
        selectedMember.addUserToClubAsLeaderFirebase(thisClubID);
        selectedMember = null;
        TextView currentMemberDisplay = findViewById(R.id.currentlyViewedMemberDisplay);
        currentMemberDisplay.setText("");
        setUpMemberList();
    }

    /**
     * Initiates the ListView of members to select from
     */
    private void setUpMemberList(){


        //region Used to set up the list of members
        members = new ArrayList<>();

        for(String memberRef : thisClub.getmIDs()){
            members.add(StudentManager.getStudent(memberRef));
        }

        ArrayAdapter<Student> membersAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, members);

        final ListView listView = findViewById(R.id.manageMembersListView);
        listView.setAdapter(membersAdapter);

        // When you click on the member, nothing happens

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMember = (members.get(position));

                TextView currentMemberDisplay = findViewById(R.id.currentlyViewedMemberDisplay);
                currentMemberDisplay.setText("Currently managing " + selectedMember.getName());

            }
        });

        //endregion End code for member display


    }

    private void noUserError(){
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
}
