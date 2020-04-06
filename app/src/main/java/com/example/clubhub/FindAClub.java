package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

public class FindAClub extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    private Spinner schoolSpinner;
    private TextView schoolText;
    List<String> school = new ArrayList<>();
    List<Club> clubs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_a_club);

        // Used to set up the school spinner (move to create an account?)

        schoolSpinner = findViewById(R.id.schoolSpinner);
        schoolText = findViewById(R.id.schoolText);


        school.add("- choose your school -");
        school.add("MAMS");
        school.add("Doherty");
        school.add("South");

        ArrayAdapter<String> schoolAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, school);
        schoolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schoolSpinner.setAdapter(schoolAdapter);

        // End code for school spinner



        // Used to set up the list of clubs


        for(String clubRef : SchoolManager.getSchool(SchoolManager.currentSchoolID).getClubs()){
            clubs.add(ClubManager.getClub(clubRef));
        }

        ArrayAdapter<Club> clubsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clubs);

        final ListView listView = findViewById(R.id.clubListView);
        listView.setAdapter(clubsAdapter);

        // When you click on the club, it opens the temp display page

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Use intent to push the club ID to the next activity to eliminate currentClubID?
                Club nClub = clubs.get(position);
                ClubManager.currentClubID = nClub.getNumID();

                Intent intent = new Intent(FindAClub.this, TempDisplayActivity.class);
                startActivity(intent);
            }
        });

        // End code for club display


    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(),school.get(position) , Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
