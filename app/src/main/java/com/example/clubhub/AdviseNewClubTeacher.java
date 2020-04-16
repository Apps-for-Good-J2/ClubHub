package com.example.clubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class AdviseNewClubTeacher extends AppCompatActivity {

    private Spinner chooseClubSpinner;
    private TextView chooseClubText;
    List<String> teacherclub = new ArrayList<>();
    List<Club> clubs = new ArrayList<>();

    FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advise_new_club_teacher);
    }


    @Override
    protected void onStart() {
        super.onStart();

        currentUser = FirebaseAuth.getInstance().getCurrentUser();


        teacherclub.add("- pick a club -");
        teacherclub.add("Math");
        teacherclub.add("Biotechnology");
        teacherclub.add("Chess");

        ArrayAdapter<String> schoolAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, teacherclub);
        schoolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseClubSpinner.setAdapter(schoolAdapter);
    }
}
