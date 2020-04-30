package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_a_club);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();


        if(StudentManager.getStudent(currentUser.getUid()) != null){
            setupClubStudent();
        }
        else{
            setupClubTeacher();
        }


        // When you click on the club, it opens the temp display page
        ArrayAdapter<Club> clubsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clubs);

        final ListView listView = findViewById(R.id.clubListView);
        listView.setAdapter(clubsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Club nClub = clubs.get(position);

                Intent intent = new Intent(FindAClub.this, ClubDescription.class);
                intent.putExtra("clubID", nClub.getNumID());
                startActivity(intent);
            }
        });

        //endregion End code for club display



    }

    private void setupClubTeacher() {
        String currentSchoolID = (TeacherManager.getTeacher(currentUser.getUid()).getSchoolID());
        ArrayList<String> schoolClubs = SchoolManager.getSchool(currentSchoolID).getClubs();
        for(String clubRef : schoolClubs){
            if(!ClubManager.getClub(clubRef).isHasTeacherAdviser()){
                clubs.add(ClubManager.getClub(clubRef));
            }
        }

        TextView statusText = findViewById(R.id.finderStatusDisplay);

        if(clubs.size() < 1){
            statusText.setText("There are no clubs at this school without an adviser");
        }
    }

    private void setupClubStudent(){
        //region Used to set up the list of clubs
        String currentSchoolID = (StudentManager.getStudent(currentUser.getUid()).getSchoolID());

        ArrayList<String> schoolClubs = SchoolManager.getSchool(currentSchoolID).getClubs();

        for(String clubRef : schoolClubs){
            if(!StudentManager.getStudent(currentUser.getUid()).isPartOfClub(clubRef) && ClubManager.getClub(clubRef) != null)
                clubs.add(ClubManager.getClub(clubRef));
        }
        TextView statusText = findViewById(R.id.finderStatusDisplay);

        if(clubs.size() < 1){
            statusText.setText("There are no clubs at this school");
        }
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
