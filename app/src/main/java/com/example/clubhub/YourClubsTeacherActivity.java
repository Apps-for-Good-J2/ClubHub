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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class YourClubsTeacherActivity extends AppCompatActivity {

    TextView displayText;
    FirebaseUser currentUser;
    Teacher thisTeacher;
    ArrayList<Club> advisingClubs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_clubs_teacher);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        displayText = findViewById(R.id.teacherSelect);
        thisTeacher = TeacherManager.getTeacher(currentUser.getUid());

    }

    @Override
    protected void onStart() {
        super.onStart();
        setupAdvisingClubList();
    }

    /**
     * Displays the list of the current teachers advising clubs
     */
    @SuppressLint("SetTextI18n")
    private void setupAdvisingClubList(){

        advisingClubs = new ArrayList<>();

        for(String clubID : thisTeacher.getAdvisingClubs()){
            advisingClubs.add(ClubManager.getClub(clubID));
        }

        if(advisingClubs.size() == 0) displayText.setText("You have no advising clubs");


        ArrayAdapter<Club> clubsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, advisingClubs);

        final ListView listView = findViewById(R.id.teacherAdvisingClubsList);
        listView.setAdapter(clubsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Club nClub = advisingClubs.get(position);

                Intent intent = new Intent(YourClubsTeacherActivity.this, ClubDescriptionActivity.class);
                intent.putExtra("clubID", nClub.getNumID());
                startActivity(intent);
            }
        });

    }

}
