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

        TextView clubNameText = findViewById(R.id.clubName);
        clubNameText.setText(thisClub.getName());

        TextView descriptionText = findViewById(R.id.descriptionFillInText);
        descriptionText.setText(thisClub.getDescription());

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        //region Used to set up the list of members

        for(String memberRef : thisClub.getmIDs()){
            members.add(UserManager.getUserData(memberRef));
        }

        ArrayAdapter<UserData> membersAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, members);

        final ListView listView = findViewById(R.id.membersListView);
        listView.setAdapter(membersAdapter);

        // When you click on the member, nothing happens

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Do something?

            }
        });

        //endregion End code for member display
    }

    public void JoinClubOnClick(View v){
        String currentUserID = currentUser.getUid();

        if(thisClub.isLeader(currentUserID) || thisClub.isMember(currentUserID))
            return;

        UserManager.getUserData(currentUserID).addUserToClubAsMemberFirebase(thisClubID);
        thisClub.addMemberFirebase(currentUserID);

        // Directs the user somewhere else or to member page for this club
        Intent intent = new Intent(this, ClubHubStudent.class);
        startActivity(intent);
    }



}
