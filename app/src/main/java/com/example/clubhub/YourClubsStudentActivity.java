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

public class YourClubsStudentActivity extends AppCompatActivity{


    private TextView yourClubText;
    List<Club> memberClubs = new ArrayList<>();
    List<Club> leaderClubs = new ArrayList<>();

    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_club_student);

        yourClubText = findViewById(R.id.yourClubs);


        currentUser = FirebaseAuth.getInstance().getCurrentUser();



    }

    @Override
    protected void onStart() {
        super.onStart();

        memberClubs.clear();
        for(String clubRef : StudentManager.getStudent(currentUser.getUid()).getmClubs()){
            Club club = ClubManager.getClub(clubRef);
            if(club != null)
                memberClubs.add(club);
        }

        leaderClubs.clear();
        for(String clubRef : StudentManager.getStudent(currentUser.getUid()).getlClubs()){
            Club club = ClubManager.getClub(clubRef);
            if(club != null)
                leaderClubs.add(club);
        }

        setShowMemberClubs();

    }

    /**
     * Displays the current user's member clubs
     */
    private void setShowMemberClubs(){

        yourClubText.setText("Your member clubs");
        ArrayAdapter<Club> clubsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, memberClubs);

        final ListView listView = findViewById(R.id.yourClubView);
        listView.setAdapter(clubsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                 Club nClub = memberClubs.get(position);

                 Intent intent = new Intent(YourClubsStudentActivity.this, ClubDescriptionActivity.class);
                 intent.putExtra("clubID", nClub.getNumID());
                 startActivity(intent);
            }
        });

    }

    /**
     * Displays the current user's leader clubs
     */
    private void setShowLeaderClubs(){

        yourClubText.setText("Your leader clubs");
        ArrayAdapter<Club> clubsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, leaderClubs);

        final ListView listView = findViewById(R.id.yourClubView);
        listView.setAdapter(clubsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Club nClub = leaderClubs.get(position);

                Intent intent = new Intent(YourClubsStudentActivity.this, ClubDescriptionActivity.class);
                intent.putExtra("clubID", nClub.getNumID());
                startActivity(intent);
            }
        });

    }

    /**
     * OnClick to display member clubs
     */
    public void memberOnClick(View v){
        setShowMemberClubs();
    }

    /**
     * OnClick to display leader clubs
     */
    public void leaderOnClick(View v){
        setShowLeaderClubs();
    }

    /**
     * Returns the user to ClubHubStudentActivity
     */
    public void backOnClick(View v){
        Intent intent = new Intent(this, ClubHubStudentActivity.class);
        startActivity(intent);
    }

}