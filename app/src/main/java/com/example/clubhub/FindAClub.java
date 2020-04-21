package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class FindAClub extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    private Spinner schoolSpinner;
    private TextView schoolText;
    List<String> school = new ArrayList<>();
    List<Club> clubs = new ArrayList<>();

    FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_a_club);


        //region Used to set up the school spinner (remove from here?)

        schoolSpinner = findViewById(R.id.schoolSpinner);
        schoolText = findViewById(R.id.subjectText);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();


        school.add("- pick a subject -");
        school.add("Math");
        school.add("Biotechnology");
        school.add("Chess");

        ArrayAdapter<String> schoolAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, school);
        schoolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schoolSpinner.setAdapter(schoolAdapter);

        //endregion End code for school spinner



        //region Used to set up the list of clubs
        String currentSchoolID = (StudentManager.getStudent(currentUser.getUid()).getSchoolID());


        for(String clubRef : SchoolManager.getSchool(currentSchoolID).getClubs()){
            if(!StudentManager.getStudent(currentUser.getUid()).isPartOfClub(clubRef) && ClubManager.getClub(clubRef) != null)
                clubs.add(ClubManager.getClub(clubRef));
        }

        Log.d("TEST", clubs.toString());


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

                Intent intent = new Intent(FindAClub.this, JoinClubDescriptionPage.class);
                intent.putExtra("clubID", nClub.getNumID());
                startActivity(intent);
            }
        });

        //endregion End code for club display**/



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
