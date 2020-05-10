package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

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
        thisClub = ClubManager.getClub(thisClubID);
        currentUser = StudentManager.getStudent(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
    }

    /**
     * Makes the current club leader a member only if there is another leader
     */
    public void revertToMember(View v){
        if(checkNumLeaders()) return;
        thisClub.addMemberFirebase(currentUser.getID());
        currentUser.addUserToClubAsMemberFirebase(thisClubID);
        leaveClubLeader(v);
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


}



