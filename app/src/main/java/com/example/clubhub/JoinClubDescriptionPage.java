package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class JoinClubDescriptionPage extends AppCompatActivity {
    List<Club> members = new ArrayList<>();
    private ClubManager MembersManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_club_description_page);

        // Used to set up the list of clubs


        for(String membersRef : SchoolManager.getSchool(SchoolManager.currentSchoolID).getMembers()){
            members.add(MembersManager.getClub(membersRef));
        }

        //change
        ArrayAdapter<Club> membersAdapter =
                new ArrayAdapter<Club>(this, android.R.layout.simple_list_item_1, members);

        final ListView listView = findViewById(R.id.membersListView);
        listView.setAdapter(membersAdapter);
}
}
