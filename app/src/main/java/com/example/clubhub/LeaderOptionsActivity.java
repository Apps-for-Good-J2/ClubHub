package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LeaderOptionsActivity extends AppCompatActivity {
    private String thisClubID;
    private Club thisClub;
    private Student currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_options);
        Intent intent = getIntent();
        thisClubID = intent.getStringExtra("clubID");

    }

    @Override
    protected void onStart() {
        super.onStart();

        thisClub = ClubManager.getClub(thisClubID);
        currentUser = StudentManager.getStudent(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        if(!thisClub.isLeader(currentUser.getID())){
            noUserError();
        }
    }

    /**
     * Makes the current club leader a member only if there is another leader
     */
    public void revertToMember(View v){
        if(checkNumLeaders()) return;

        thisClub.deleteLeaderFirebase(currentUser.getID());
        currentUser.removeClubFromLeaderFirebase(thisClubID);

        thisClub.addMemberFirebase(currentUser.getID());
        currentUser.addUserToClubAsMemberFirebase(thisClubID);

        Intent intent = new Intent(this, YourClubsStudentActivity.class);
        startActivity(intent);
    }

    /**
     * Makes the current club leader leave only if there is another leader
     */
    public void leaveClubLeader(View v){
        if(checkNumLeaders()) return;
        thisClub.deleteLeaderFirebase(currentUser.getID());
        currentUser.removeClubFromLeaderFirebase(thisClubID);
        Intent intent = new Intent(this, YourClubsStudentActivity.class);
        startActivity(intent);
    }

    /**
     * Checks if there is more than 1 leader in the current club
     * @return true if there is only 1 leader, false otherwise
     */
    private boolean checkNumLeaders(){
        if(thisClub.getlIDs().size() == 1){
            Toast.makeText(this, "You need at least 1 student leader", Toast.LENGTH_SHORT).show();
        }
        return thisClub.getlIDs().size() == 1;
    }


    private void noUserError() {
        Log.d("Edit", "noUserError called");
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        Intent intentBackup = new Intent(this, ClubHubStudentActivity.class);
        startActivity(intentBackup);
    }


}



